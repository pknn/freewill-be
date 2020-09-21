package routers

import com.google.inject.Inject
import controllers.ApiController
import play.api.routing.SimpleRouter
import play.api.routing.Router._
import play.api.routing.sird._

class ApiRouter @Inject() (
  apiController: ApiController,
  versionRouter: VersionRouter
) extends SimpleRouter {
  override def routes: Routes = {
    versionRouter
      .withPrefix("/version")
      .routes
      .orElse {
        case GET(p"/") => apiController.index
      }
  }
}
