package configurations

import com.google.inject.{Inject, Singleton}
import com.typesafe.config.Config
import play.api.{ConfigLoader, Configuration}

@Singleton()
class ApplicationConfiguration @Inject() (configuration: Configuration) {
  lazy val app: AppConfig = configuration.get[AppConfig]("app")
}

case class AppConfig(secretKey: String)

object AppConfig {
  def apply(config: Config): AppConfig = AppConfig(config.getString("secretKey"))

  implicit val configLoader: ConfigLoader[AppConfig] =
    (rootConfig: Config, path: String) => { AppConfig(rootConfig.getConfig(path)) }

}
