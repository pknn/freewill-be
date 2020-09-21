package commons

import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.Result
import play.api.mvc.Results._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

object ApiResults {

  def json[C](content: C)(implicit writes: Writes[C]): Result = {
    Ok(successResultJsonContent(content))
  }

  def resultAsync[C](futureContent: Future[C])(implicit
      writes: Writes[C],
      ec: ExecutionContext
  ): Future[Result] = {
    futureContent
      .map(content => Ok(successResultJsonContent(content)))
      .recover {
        case throwable: Throwable =>
          BadRequest(failResultJsonContent(throwable))
      }
  }

  private def successResultJsonContent[C](
      content: C
  )(implicit writes: Writes[C]): JsValue =
    Json.obj("result" -> Json.toJson(content))

  private def failResultJsonContent(throwable: Throwable): JsValue =
    Json.obj("message" -> throwable.toString)
}
