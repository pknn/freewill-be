package routers

import com.google.inject.Inject
import controllers.UserController
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird.{GET, _}

class UserRouter @Inject() (userController: UserController) extends SimpleRouter {
  def routes: Routes = {
    case GET(p"/") => userController.get
  }

}
