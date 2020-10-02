package exceptions

sealed abstract class VersionException(
  code: String = "VERSION_ERROR",
  message: String = "Version Error",
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

case class VersionNotFoundException(cause: Throwable = null)
    extends VersionException(
      code = "VERSION_NOT_FOUND",
      message = "Version not found",
      cause
    )
