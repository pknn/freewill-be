package commons

import java.security.SecureRandom
import java.util.Base64

import exceptions.{AuthenticationInvalidCredentialException, AuthenticationMalformedPasswordException}
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object Cryptography {
  private val defaultIteration = 10000
  private val random = new SecureRandom()

  private def pbkdf2(
    password: String,
    salt: Array[Byte],
    iterations: Int
  ): Array[Byte] = {
    val keySpec = new PBEKeySpec(password.toCharArray, salt, iterations, 256)
    val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    keyFactory.generateSecret(keySpec).getEncoded
  }

  def encryptPassword(password: String): String = {
    val salt = new Array[Byte](16)
    random.nextBytes(salt)
    val hashedPassword: Array[Byte] = pbkdf2(password, salt, defaultIteration)
    val base64Salt: String = Base64.getEncoder.encodeToString(salt)
    val base64HashedPassword: String = Base64.getEncoder.encodeToString(hashedPassword)

    s"$defaultIteration:$base64HashedPassword:$base64Salt"
  }

  def checkPassword(password: String, encryptedPassword: String): Boolean = {
    val splitEncryptedPassword: Array[String] = encryptedPassword.split(":")
    splitEncryptedPassword match {
      case Array(iteration, base64HashedPassword, base64Salt) if iteration.forall(_.isDigit) =>
        val hashedPassword: Array[Byte] = Base64.getDecoder.decode(base64HashedPassword)
        val salt: Array[Byte] = Base64.getDecoder.decode(base64Salt)

        val hashedCheckPassword: Array[Byte] = pbkdf2(password, salt, iteration.toInt)
        if (hashedCheckPassword.sameElements(hashedPassword)) true
        else throw new AuthenticationInvalidCredentialException
      case _                                                                                 => throw new AuthenticationMalformedPasswordException
    }
  }

}
