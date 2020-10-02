package presenters

import models.Authentication
import play.api.libs.json.{Format, Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase

case class AuthenticationPresenter(accessToken: String, refreshToken: String)

object AuthenticationPresenter {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[AuthenticationPresenter] = Json.format[AuthenticationPresenter]

  def apply(authentication: Authentication): AuthenticationPresenter =
    AuthenticationPresenter(authentication.accessToken, authentication.refreshToken)

}
