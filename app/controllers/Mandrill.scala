package controllers

import scala.concurrent.Future
import play.api.Play
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.{ Response, WS }
import play.api.libs.json._

object Mandrill {

	private val mandrillApiKey = "mandrill.apiKey"
	private val mandrillApiEndpoint = "https://mandrillapp.com/api/1.0/"

	private def generateEndpoint(module: String, action: String) : String = {
		return mandrillApiEndpoint + module + "/" + action + ".json"
	}

	def sendEmail(email: String): Future[Response] = {
		val params = Json.obj(
			"key" -> getStringConfigValue(mandrillApiKey),
			"message" -> Json.obj(
				"html" -> (
					"<p>Hi</p>!" +
					"</p>I'm Joao, co-founder of Wazza, tt's great to have with us! " +
					"Our mission is to help mobile companies increase in-app purchase revenue in a smarter way. " +
					"At the moment, we are building the first version of our product and will notify you when it is ready. " +
					"We love talking to customers. Feel free to contact me anytime via email or Skype (joao.v.vasques)</p>" +
					"<p>Follow us on " +
					"<a href='https://angel.co/wazza'>AngelList</a>, " +
					"<a href='https://twitter.com/UseWazza'>Twitter</a> " +
					"and <a href='https://www.facebook.com/usewazza'>Facebook</a>" +
					"to get our latest news</p> " +
					"<p></p>"
				),
				"subject" -> "Welcome to Wazza!",
				"from_email" -> "joao@usewazza.com",
				"from_name" -> "Wazza",
				"to" -> Json.arr(
					Json.obj(
						"email" -> email,
						"type" -> "to"
					)
				)
			)
		)

		WS.url(generateEndpoint("messages", "send")).post(params)
	}

	def sendNotification(email: String): Future[Response] = {
		val params = Json.obj(
			"key" -> getStringConfigValue(mandrillApiKey),
			"message" -> Json.obj(
				"text" -> ("A new user with email " + email + " has signed up"),
				"subject" -> "New Sign up",
				"from_email" -> "no-reply@usewazza.com",
				"from_name" -> "Wazza",
				"to" -> Json.arr(
					Json.obj(
						"email" -> "joao@usewazza.com",
						"type" -> "to"
					)
				)
			)
		)

		WS.url(generateEndpoint("messages", "send")).post(params)
	}

	private def getStringConfigValue(key: String): Option[String] = Play.current.configuration.getString(key)
}
