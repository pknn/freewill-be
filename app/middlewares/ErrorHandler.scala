package middlewares

import exceptions.{ApplicationException, ExpectedException}
import javax.inject._
import play.api._
import play.api.http.JsonHttpErrorHandler
import play.api.i18n.{Langs, MessagesApi, MessagesImpl, MessagesProvider}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router
import presenters.FailResponsePresenter

import scala.concurrent._

@Singleton()
class ErrorHandler @Inject() (
  env: Environment,
  config: Configuration,
  sourceMapper: OptionalSourceMapper,
  router: Provider[Router],
  langs: Langs,
  messagesApi: MessagesApi)
    extends JsonHttpErrorHandler(env, sourceMapper) {
  private val defaultLang = langs.availables.head

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    val lang = request.acceptLanguages.filter(langs.availables.contains) match {
      case Nil       => defaultLang
      case head :: _ => head
    }

    implicit val messagesProvider: MessagesProvider = MessagesImpl(lang, messagesApi)

    exception match {
      case e: ExpectedException    =>
        val failResponse = FailResponsePresenter(
          errorCode = e.apiErrorCode,
          message = e.apiErrorMessage
        )

        Future.successful {
          BadRequest(
            Json.toJson(failResponse)
          )
        }
      case e: ApplicationException =>
        val failResponse = FailResponsePresenter(
          errorCode = e.apiErrorCode,
          message = "Something went wrong. Please try again."
        )

        Future.successful {
          BadRequest(
            Json.toJson(failResponse)
          )
        }
      case _                       => super.onServerError(request, exception)
    }
  }

}
