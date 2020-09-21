package controllers

import com.google.inject.{Singleton, Inject}
import play.api.mvc._
import commons.ApiResults

@Singleton()
class ApiController @Inject() (
  val controllerComponents: ControllerComponents
) extends BaseController {
  def index: Action[AnyContent] =
    Action {
      ApiResults.json("Hello")
    }
}
