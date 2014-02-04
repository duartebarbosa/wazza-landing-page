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
					"I’m Joao and thanks signing up for Wazza. " +
					"Our mission is to help mobile app developers, like you, increase in-app purchase revenue of their amazing mobile apps without" +
					" destroying the joy and happiness of their users. " +
					"<p>At the moment, we are <b>coding like hell</b> to build the first version of our product." +
					"You will be notified when our platform is ready for testing! For now, I can only ask you to help us spread the word amongst your network" +
					"Help us now so we can help help you later.</p>" +
					// "<p>Please reply to this email to let us know <b>what is hurting your team and how can we help you</b>. Help us now so we can help you later.</p>" +
					"<p>Also, feel free to contact me via Skype: joao.v.vasques</p>" + 
					"<p>Stay tuned, more news coming soon</p>"
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
