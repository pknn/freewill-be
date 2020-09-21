package controllers

import com.google.inject.{Singleton, Inject}
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents
import play.api.mvc.Action
import play.api.mvc.AnyContent
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
