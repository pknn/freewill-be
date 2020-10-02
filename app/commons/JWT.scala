package commons

import java.time.Clock

import configurations.ApplicationConfiguration
import exceptions.{AuthenticationInvalidAccessTokenException, AuthenticationInvalidRefreshTokenException}
import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtJson}
import play.api.libs.json.JsObject
import play.api.libs.json.JsValue.jsValueToJsLookup

import scala.util.{Failure, Success, Try}

object JWT {
  implicit val clock: Clock = Clock.systemUTC
  private val algorithm = JwtAlgorithm.HS256
  private val accessTokenLifetime = 1800
  private val refreshTokenLifetime = 3600

  def getTokens(userId: String)(implicit applicationConfiguration: ApplicationConfiguration): (String, String) =
    (getAccessToken(userId), getRefreshToken(userId))

  def getAccessToken(userId: String)(implicit applicationConfiguration: ApplicationConfiguration): String = {
    val jwtClaim: JwtClaim = getJwtClaim(userId, accessTokenLifetime)
    val secretKey = applicationConfiguration.app.secretKey
    JwtJson.encode(jwtClaim, secretKey, algorithm)
  }

  def getRefreshToken(userId: String)(implicit applicationConfiguration: ApplicationConfiguration): String = {
    val jwtClaim: JwtClaim = getJwtClaim(userId, refreshTokenLifetime)
    val secretKey = applicationConfiguration.app.secretKey
    JwtJson.encode(jwtClaim, secretKey, algorithm)
  }

  def validateAccessToken(
    token: String,
    userId: String
  )(implicit applicationConfiguration: ApplicationConfiguration
  ): Try[Boolean] =
    decodeJWT(token).flatMap { content =>
      (content \ "userId").asOpt[String] match {
        case Some(value) if value == userId => Success(true)
        case _                              => Failure(new AuthenticationInvalidAccessTokenException)
      }
    }

  def validateRefreshToken(
    token: String,
    userId: String
  )(implicit applicationConfiguration: ApplicationConfiguration
  ): Try[Boolean] =
    decodeJWT(token).flatMap { content =>
      (content \ "userId").asOpt[String] match {
        case Some(value) if value == userId => Success(true)
        case _                              => Failure(new AuthenticationInvalidRefreshTokenException)
      }
    }

  private def decodeJWT(token: String)(implicit applicationConfiguration: ApplicationConfiguration): Try[JsObject] = {
    val secretKey = applicationConfiguration.app.secretKey
    JwtJson.decodeJson(token, secretKey, Seq(algorithm))
  }

  private def getJwtClaim(userId: String, lifetime: Long) =
    JwtClaim(s"""{"userId": "$userId}"""").issuedNow.expiresIn(lifetime)

}
