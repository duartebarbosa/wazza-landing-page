package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

	val loginForm = Form(
	  tuple(
	    "name" -> text,
	    "email" -> text
	  )
	)

  def index = Action {
    Ok(views.html.index(loginForm))
  }

  def submit = TODO

}