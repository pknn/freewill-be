package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import play.api.mvc.BaseController
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import useCases.VersionUseCase
import presenters.VersionPresenter
import commons.ApiResults
import bodies.CreateVersionBody

@Singleton()
class VersionController @Inject() (
  val controllerComponents: ControllerComponents,
  versionUseCase: VersionUseCase
)(implicit ec: ExecutionContext)
    extends BaseController {
  def get: Action[AnyContent] =
    Action.async {
      val version = versionUseCase.getLatestVersion
      val versionPresenter = version.map(VersionPresenter.apply)

      ApiResults.async(versionPresenter)
    }

  def post: Action[CreateVersionBody] =
    Action.async(parse.json[CreateVersionBody]) { request =>
      val body: CreateVersionBody = request.body
      val result: Future[Unit] = versionUseCase.addVersion(body.appVersion)

      ApiResults.async(result.map(_ => "Success"))
    }

}
