package controllers

import bodies.AuthenticationBody
import com.google.inject.{Inject, Singleton}
import commons.ApiResults
import play.api.mvc.{Action, BaseController, ControllerComponents}
import presenters.AuthenticationPresenter
import useCases.AuthenticationUseCase

import scala.concurrent.ExecutionContext

@Singleton()
class AuthenticationController @Inject() (
  val controllerComponents: ControllerComponents,
  authenticationUseCase: AuthenticationUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def login: Action[AuthenticationBody] =
    Action.async(parse.json[AuthenticationBody]) { request =>
      val body = request.body
      val authenticationResult = authenticationUseCase.login(body.username, body.password)
      val authenticationResultPresenter = authenticationResult.map(AuthenticationPresenter.apply)

      ApiResults.async(authenticationResultPresenter)
    }

}
