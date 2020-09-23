package presenters

import play.api.libs.json.JsonNaming.SnakeCase
import play.api.libs.json.{Format, Json, JsonConfiguration}

case class FailResponsePresenter(
  errorCode: String,
  message: String
)

object FailResponsePresenter {

  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[FailResponsePresenter] =
    Json.format[FailResponsePresenter]

}
