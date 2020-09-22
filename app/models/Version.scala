package models

import persists.generated.Tables.VersionRow

case class Version(appVersion: String)

object Version {
  def apply(versionRow: VersionRow): Version =
    Version(appVersion = versionRow.appVersion)

}
