package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import play.api.mvc.BaseController
import play.api.mvc._
import useCases.VersionUseCase
import presenters.VersionPresenter
import scala.concurrent.ExecutionContext
import commons.ApiResults

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

      ApiResults.resultAsync(versionPresenter)
    }
}
