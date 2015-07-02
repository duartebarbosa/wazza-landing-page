package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.Logger
import play.api.mvc.Controller
import play.api.mvc.Action
import play.api.Play.current
import anorm._
import play.api.libs.concurrent.Execution.Implicits._

  case class User(
    name: String,
    email: String,
    company: String,
    promocode: Option[String],
    country: String
  )

object Application extends Controller {
  def isValid(email: String): Boolean = {
    if("""(?=[^\s]+)(?=(\w+)@([\w\.]+))""".r.findFirstIn(email) == None) {
      false
    } else true
  }

  def index = Action {
    Ok(views.html.index())
  }

  def payment_provider = Action {
    Ok(views.html.index_pp())
  }

  def mobile_developer = Action {
    Ok(views.html.index_app())
  }

  def register = Action {
    Ok(views.html.register())
  }

  def docs = Action {
    Ok(views.html.documentation())
  }

  def demo = Action {
    Ok(views.html.demo())
  }

  val registrationForm: Form[User] = Form(
      mapping(
        "name" -> nonEmptyText,
        "email" -> nonEmptyText,
        "company" -> nonEmptyText,
        "promocode" -> optional(text),
        "country" -> nonEmptyText
      )(User.apply)(User.unapply)
  )

  def registerSubmit = Action{ implicit request =>
    registrationForm.bindFromRequest.fold(
      errors => {
        BadRequest(views.html.index())
      },
      user => {
        if(isValid(user.email)){
          println("valid email")
          Ok
        } else {
          BadRequest
        }
      }
    )
  }
}

object DatabaseService {

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

  def registration(user: User) : Unit = {
    val notification = Mandrill.sendRegisterNotification(user.name, user.email, user.company, user.promocode)
    println("Notification email - registration")
    notification.map({
      notification => println(notification.body)
    })
  }
}
