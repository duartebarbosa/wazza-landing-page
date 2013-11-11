package controllers

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.Logger
import play.api.libs.json._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.util.ArrayList
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair
import play.api.mvc.Controller
import play.api.mvc.Action

object Application extends Controller {
   
  val loginForm = Form(
    single(
        "email" -> text
	) verifying(data => isValid(data))
  )
	
  def isValid(email: String): Boolean =
    if("""(?=[^\s]+)(?=(\w+)@([\w\.]+))""".r.findFirstIn(email) == None)false else true

  def index = Action {
 	Logger info "Hello!"
    Ok(views.html.index(loginForm))
  }

  def submit = Action{ implicit request => 
    loginForm.bindFromRequest.fold(
        errors => BadRequest( "You need to pass a 'xxx' value!" ),
        email => {
          Logger.info("success")
          Logger.info(email.toString())
          Mailchimp.subscribe(email)
          Ok(views.html.index(loginForm))
        }
    )
  }
}

object Mailchimp {
  private val apiVersion = "2.0"
  private val test = "/helper/ping.json"
  private val apiEndpoint = "https://us5.api.mailchimp.com/" + apiVersion + test//"/lists/subscribe"
  private val apiKey = "c03102bf486bcce1b0192bc223f8891e"
  private val listId = "c0fb7d3690"
 
  def subscribe(email: String) : Unit = {
	 val request =   Json.obj(
	     "apikey" -> apiKey
//	      "id" -> listId,
//	      "email" -> Json.obj(
//	          "email" -> email,
//	          "euid" -> email,
//	          "leid" -> email  
//	      )
	)
    //lists/subscribe 
	val post = new HttpPost(apiEndpoint)
	val client = new DefaultHttpClient	

    // add name value pairs
    val nameValuePairs = new ArrayList[NameValuePair]()
    nameValuePairs.add(new BasicNameValuePair("JSON", request.toString));
    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    
    // send the post request
    val response = client.execute(post)
    println("RESPONSE")
    println("--- HEADERS ---")
    response.getAllHeaders.foreach(println(_))    
  }  
}
