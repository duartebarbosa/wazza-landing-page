package controllers

import scala.concurrent.Future
import play.api.Play
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.{ Response, WS }
import play.api.libs.json._

object MailChimp {
  private val MailChimpConfigListId = "mailchimp.listId"
  private val MailChimpConfigApiKey = "mailchimp.apiKey"
    
  private val ApiKeyFormat = "[^-]*-(.*)".r
  private val MailChimpApiEndPoint = "https://%s.api.mailchimp.com/2.0/"
  private def MailChimpApi(dataCenter: String): String = MailChimpApiEndPoint.format(dataCenter)
  private val ListSubscribe = "/lists/subscribe.json"

  def subscribe(email: String): Option[Future[Response]] =    
    
    for {
      listId <- getStringConfigValue(MailChimpConfigListId)
      apiKey <- getStringConfigValue(MailChimpConfigApiKey)
      listSubscribeMethod <- createListSubscribeMethod(apiKey)
    } yield {
      val params = Json.obj(
          "apikey" -> apiKey,
          "id" -> listId,
		  "double_optin" -> false,
		  "email" -> Json.obj(
		      "email" -> email,
		      "euid" -> email,
		      "leid" -> 123
		  )
	  )

	  WS.url(listSubscribeMethod).post(params)
    }

  private def createListSubscribeMethod(apiKey: String): Option[String] =
    for (ApiKeyFormat(dataCenter) <- ApiKeyFormat.findFirstIn(apiKey))
      yield MailChimpApi(dataCenter) + ListSubscribe

  private def getStringConfigValue(key: String): Option[String] = Play.current.configuration.getString(key)
}