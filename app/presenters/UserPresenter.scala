package presenters

import models.User
import play.api.libs.json.{Format, Json, JsonConfiguration}
import play.api.libs.json.JsonNaming.SnakeCase

case class UserPresenter(username: String, email: String)

object UserPresenter {
  implicit val jsonConfig: JsonConfiguration = JsonConfiguration(SnakeCase)
  implicit val jsonFormat: Format[UserPresenter] = Json.format[UserPresenter]

  def apply(user: User): UserPresenter = UserPresenter(user.username, user.email)
}
