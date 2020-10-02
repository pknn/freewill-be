package commons

import exceptions.{
  AuthenticationInvalidEmailException,
  AuthenticationInvalidPasswordLengthRequiredException,
  AuthenticationInvalidPasswordePatternException,
  AuthenticationInvalidUsernameLengthRequiredException,
  AuthenticationInvalidUsernamePatternException
}

object Validation {

  def validateUsername(username: String): Unit = {
    if (username.length < 6 || username.length > 15) throw new AuthenticationInvalidUsernameLengthRequiredException
    if (!username.matches("([\\w_-]){4,15}")) throw new AuthenticationInvalidUsernamePatternException
  }

  def validatePassword(password: String): Unit = {
    if (password.length < 6 || password.length > 15) throw new AuthenticationInvalidPasswordLengthRequiredException
    if (!password.matches("([\\w_-]){4,15}")) throw new AuthenticationInvalidPasswordePatternException
  }

  def validateEmail(email: String): Unit =
    if (
      !email.matches(
        "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
      )
    ) throw new AuthenticationInvalidEmailException

}
