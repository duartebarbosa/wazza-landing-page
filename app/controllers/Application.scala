package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.Logger
import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.db.DB
import play.api.Play.current
import anorm._ 
import play.api.libs.concurrent.Execution.Implicits._

object Application extends Controller {
   
  val loginForm = Form(
    single(
        "email" -> text
	  ) verifying("Please insert a valid email!", data => isValid(data))
  )
	
  def isValid(email: String): Boolean =
    if("""(?=[^\s]+)(?=(\w+)@([\w\.]+))""".r.findFirstIn(email) == None) {
      false 
    } else {
      ! DatabaseService.exists(email)
    }

  def index = Action {
    Ok(views.html.index(loginForm)("hello world"))
  }

  def submit = Action{ implicit request => 
    loginForm.bindFromRequest.fold(
        errors => {
          println(errors.toString)
          BadRequest(views.html.index(errors)(("hello world")))
        },
        email => {
          DatabaseService.save(email)
          Ok(views.html.index(loginForm)("wazza"))
        }
    )
  }
}


object DatabaseService {
  
  private def init() {
    DB.withConnection { implicit conn =>
      val query = "CREATE TABLE IF NOT EXISTS Contacts(user_id SERIAL NOT NULL PRIMARY KEY,email varchar(225) NOT NULL UNIQUE)"
      SQL(query).execute
    }
  }
  
  def getAllContacts() : Unit = {
    DB.withConnection { implicit conn =>
      val selectedContacts = SQL("Select * from Contacts")
      val contacts = selectedContacts().map(_[String]("email")).toList
      println(contacts.toString) 
    }
  }
  
  def exists(email: String) : Boolean = {
    init
    DB.withConnection { implicit conn =>
      val number = SQL("Select * from Contacts Where email='" + email + "'")().map( _ [String]("email")).toList.size
      
      number match {
        case 1 => true
        case _ => false
      }
    }
  }
  
  def save(email: String) : Unit = {
    init
    DB.withConnection { implicit conn =>            
      val id: Option[Long] = SQL("insert into Contacts(email) values ({email})")
              .on('email -> email).executeInsert()
      getAllContacts
      updateMailchimpContacts(email)
      sendNotificationEmail(email)
    }
  }
  
  private def updateMailchimpContacts(email: String) : Unit = {
    val response = MailChimp.subscribe(email)
    println("Mailchimp update notification")
    response.get.map({
      response => println(response.body)
    })
  }
  
  private def sendNotificationEmail(emailContact: String) : Unit = {
    val response = Mandrill.sendEmail(emailContact)
    println("Email to user")
    response.map({
      response => println(response.body)
    })

    val notification = Mandrill.sendNotification(emailContact)
    println("Notification email")
    notification.map({
      notification => println(notification.body)
    })
  }
}
