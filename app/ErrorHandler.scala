import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent._

class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    if(statusCode == play.api.http.Status.NOT_FOUND) {
  		Future.successful(NotFound(
      		views.html.notFoundPage(request.path)
    	))
	}
	else {
		Future.successful(
	      Status(statusCode)("A client error occurred: " + message)
	    )
	}
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}