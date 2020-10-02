package routers

import com.google.inject.Inject
import controllers.ApiController
import play.api.routing.SimpleRouter
import play.api.routing.Router._
import play.api.routing.sird._

class ApiRouter @Inject() (
  apiController: ApiController,
  versionRouter: VersionRouter,
  topicRouter: TopicRouter)
    extends SimpleRouter {
  override def routes: Routes =
    versionRouter
      .withPrefix("/version")
      .routes
      .orElse(topicRouter.withPrefix("/topics").routes)
      .orElse {
        case GET(p"/") => apiController.index
      }

}
