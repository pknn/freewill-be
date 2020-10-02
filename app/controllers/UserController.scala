package controllers

import com.google.inject.{Inject, Singleton}
import commons.ApiResults
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import presenters.UserPresenter
import useCases.UserUseCase

import scala.concurrent.ExecutionContext

@Singleton()
class UserController @Inject() (
  protected val controllerComponents: ControllerComponents,
  userUseCase: UserUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def get: Action[AnyContent] =
    Action.async {
      val users = userUseCase.findUsers()
      val usersPresenters = users.map(_.map(UserPresenter.apply))

      ApiResults.async(usersPresenters)
    }

}
