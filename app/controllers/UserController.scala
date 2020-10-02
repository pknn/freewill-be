package controllers

import bodies.UserBody
import com.google.inject.{Inject, Singleton}
import commons.ApiResults
import models.UserForm
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

  def create: Action[UserBody] =
    Action.async(parse.json[UserBody]) { request =>
      val body = request.body
      val userForm = UserForm(body)
      val result = userUseCase.createUser(userForm)

      ApiResults.async(result.map(_ => "Success"))
    }

  def edit(userId: String): Action[UserBody] =
    Action.async(parse.json[UserBody]) { request =>
      val body = request.body
      val userForm = UserForm(body)
      val result = userUseCase.editUser(userId, userForm)

      ApiResults.async(result.map(_ => "Success"))
    }

  def delete(userId: String): Action[AnyContent] =
    Action.async {
      val result = userUseCase.deleteUser(userId)

      ApiResults.async(result.map(_ => "Success"))
    }

}
