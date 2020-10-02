package exceptions

sealed abstract class AuthenticationException(
  code: String = "AUTH_ERROR",
  message: String = "Authentication Error",
  cause: Throwable = null,
  errorMessageKey: String = "",
  errorMessageParams: Seq[String] = Seq.empty)
    extends ExpectedException(
      code,
      message,
      cause,
      errorMessageKey,
      errorMessageParams
    )

case class AuthenticationInvalidCredentialException(cause: Throwable = null)
    extends AuthenticationException(code = "AUTH_INVALID_CREDENTIAL", message = "Invalid Credential", cause = cause)

case class AuthenticationMalformedPasswordException(cause: Throwable = null)
    extends AuthenticationException(code = "AUTH_MALFORMED_PASSWORD", message = "Malformed password", cause = cause)

case class AuthenticationInvalidUsernameLengthRequiredException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_USERNAME_LENGTH",
      message = "Username must be 6 - 15 characters.",
      cause = cause
    )

case class AuthenticationInvalidUsernamePatternException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_USERNAME_PATTERN",
      message = "Username must be composed of alphanumeric, underscore, or hyphen only.",
      cause = cause
    )

case class AuthenticationInvalidPasswordLengthRequiredException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_PASSWORD_LENGTH",
      message = "Password length must be 6 - 15 characters",
      cause = cause
    )

case class AuthenticationInvalidPasswordePatternException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_PASSWORD_PATTERN",
      message = "Username must be composed of alphanumeric, underscore, or hyphen only.",
      cause = cause
    )

case class AuthenticationInvalidEmailException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_EMAIL_PATTERN",
      message = "Email address is invalid",
      cause = cause
    )

case class AuthenticationUserNotFoundException(cause: Throwable = null)
    extends AuthenticationException(code = "AUTH_USER_NOT_FOUND", message = "User not found", cause = cause)

case class AuthenticationInvalidAccessTokenException(cause: Throwable = null)
    extends AuthenticationException(code = "AUTH_INVALID_ACCESS_TOKEN", message = "Invalid Access Token", cause = cause)

case class AuthenticationInvalidRefreshTokenException(cause: Throwable = null)
    extends AuthenticationException(
      code = "AUTH_INVALID_REFRESH_TOKEN",
      message = "Invalid Refresh Token",
      cause = cause
    )
