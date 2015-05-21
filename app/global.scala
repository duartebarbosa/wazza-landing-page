import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.filters.headers.SecurityHeadersFilter
import scala.concurrent.Future

object Global extends WithFilters(SecurityHeadersFilter()) with GlobalSettings {

  override def onHandlerNotFound(request: RequestHeader) = {
    Future.successful(NotFound(
      views.html.notFoundPage(request.path)
    ))
  }

}