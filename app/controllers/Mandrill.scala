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
					"<p>Hi!</p>" + 
					"I’m Joao and I want to thank you for signing up for Wazza. " + 
					"Me and my team are working hard to help mobile app developers, like you, increase revenue of their amazing mobile apps in an easy and quick way." +
					"<p>Please reply to this email to let us know <b>what is hurting your team and how can we help you</b>. Help us now so we can help you later.</p>" +
					"<p>Feel free to contact me via Skype: joao.v.vasques</p>" + 
					"<p>Stay tuned!</p>"
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
