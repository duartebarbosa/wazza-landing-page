package controllers

import play.api.libs.json.Json
import play.api.Play
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.{ Response, WS }

object SendGrid {
  private val sendGridConfigUser = "sendgrid.user"
  private val sendGridConfigApiKey = "sendgrid.apikey"
    
  private val apiEndpoint = "https://api.sendgrid.com/api/"
  private val format = "json"
    
  private def generateEndpoint(module: String, action: String) : String = {
    return apiEndpoint + module + "." + action + "." + format
  }
  
  def sendEmail(emailContact: String) : Future[Response] = {
    val params = Json.obj(
        "api_user" -> getStringConfigValue(sendGridConfigUser),
        "api_key" -> getStringConfigValue(sendGridConfigApiKey),
        "to" -> "joao@usewazza.com",
        "toname" -> "Test",
        "subject" -> "Test",
        "text" -> ("A person with this " + emailContact + " email just signed up"),
        "from" -> "no-reply@usewazza.com"
    )
    
    println(params)
    println(generateEndpoint("mail", "send"))
    
    WS.url(generateEndpoint("mail", "send")).post(params)
  }
  
  private def getStringConfigValue(key: String): Option[String] = Play.current.configuration.getString(key)

}
