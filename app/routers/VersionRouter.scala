package routers

import com.google.inject.Inject
import play.api.routing.SimpleRouter
import play.api.routing.Router._
import play.api.routing.sird._
import controllers.VersionController

class VersionRouter @Inject() (
  versionController: VersionController
) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/")  => versionController.get
    case POST(p"/") => versionController.post
  }

}
