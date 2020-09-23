package persists.generated
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = PlayEvolutions.schema ++ Topics.schema ++ Version.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table PlayEvolutions
    *  @param id Database column id SqlType(int4), PrimaryKey
    *  @param hash Database column hash SqlType(varchar), Length(255,true)
    *  @param appliedAt Database column applied_at SqlType(timestamp)
    *  @param applyScript Database column apply_script SqlType(text), Default(None)
    *  @param revertScript Database column revert_script SqlType(text), Default(None)
    *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
    *  @param lastProblem Database column last_problem SqlType(text), Default(None)
    */
  case class PlayEvolutionsRow(
    id: Int,
    hash: String,
    appliedAt: java.sql.Timestamp,
    applyScript: Option[String] = None,
    revertScript: Option[String] = None,
    state: Option[String] = None,
    lastProblem: Option[String] = None)

  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(
    implicit e0: GR[Int],
    e1: GR[String],
    e2: GR[java.sql.Timestamp],
    e3: GR[Option[String]]
  ): GR[PlayEvolutionsRow] =
    GR {
      prs =>
        import prs._
        PlayEvolutionsRow.tupled((
          <<[Int],
          <<[String],
          <<[java.sql.Timestamp],
          <<?[String],
          <<?[String],
          <<?[String],
          <<?[String]
        ))
    }

  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * =
      (
        id,
        hash,
        appliedAt,
        applyScript,
        revertScript,
        state,
        lastProblem
      ) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      ((Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem)).shaped.<>(
        { r => import r._; _1.map(_ => PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7))) },
        (_: Any) => throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255, varying = true))

    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")

    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))

    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))

    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255, varying = true), O.Default(None))

    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }

  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Topics
    *  @param id Database column id SqlType(varchar), PrimaryKey, Length(50,true)
    *  @param title Database column title SqlType(varchar), Length(100,true)
    *  @param description Database column description SqlType(text), Default(None)
    *  @param score Database column score SqlType(int4), Default(0)
    *  @param createdAt Database column created_at SqlType(timestamptz)
    */
  case class TopicsRow(
    id: String,
    title: String,
    description: Option[String] = None,
    score: Int = 0,
    createdAt: java.sql.Timestamp)

  /** GetResult implicit for fetching TopicsRow objects using plain SQL queries */
  implicit def GetResultTopicsRow(
    implicit e0: GR[String],
    e1: GR[Option[String]],
    e2: GR[Int],
    e3: GR[java.sql.Timestamp]
  ): GR[TopicsRow] =
    GR {
      prs =>
        import prs._
        TopicsRow.tupled((<<[String], <<[String], <<?[String], <<[Int], <<[java.sql.Timestamp]))
    }

  /** Table description of table topics. Objects of this class serve as prototypes for rows in queries. */
  class Topics(_tableTag: Tag) extends profile.api.Table[TopicsRow](_tableTag, "topics") {
    def * = (id, title, description, score, createdAt) <> (TopicsRow.tupled, TopicsRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      ((Rep.Some(id), Rep.Some(title), description, Rep.Some(score), Rep.Some(createdAt))).shaped.<>(
        { r => import r._; _1.map(_ => TopicsRow.tupled((_1.get, _2.get, _3, _4.get, _5.get))) },
        (_: Any) => throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(varchar), PrimaryKey, Length(50,true) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(50, varying = true))

    /** Database column title SqlType(varchar), Length(100,true) */
    val title: Rep[String] = column[String]("title", O.Length(100, varying = true))

    /** Database column description SqlType(text), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Default(None))

    /** Database column score SqlType(int4), Default(0) */
    val score: Rep[Int] = column[Int]("score", O.Default(0))

    /** Database column created_at SqlType(timestamptz) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
  }

  /** Collection-like TableQuery object for table Topics */
  lazy val Topics = new TableQuery(tag => new Topics(tag))

  /** Entity class storing rows of table Version
    *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
    *  @param appVersion Database column app_version SqlType(varchar), Length(10,true)
    *  @param createdAt Database column created_at SqlType(timestamptz)
    */
  case class VersionRow(
    id: Int,
    appVersion: String,
    createdAt: Option[java.sql.Timestamp])

  /** GetResult implicit for fetching VersionRow objects using plain SQL queries */
  implicit def GetResultVersionRow(
    implicit e0: GR[Int],
    e1: GR[String],
    e2: GR[Option[java.sql.Timestamp]]
  ): GR[VersionRow] =
    GR {
      prs =>
        import prs._
        VersionRow.tupled((<<[Int], <<[String], <<?[java.sql.Timestamp]))
    }

  /** Table description of table version. Objects of this class serve as prototypes for rows in queries. */
  class Version(_tableTag: Tag) extends profile.api.Table[VersionRow](_tableTag, "version") {
    def * = (id, appVersion, createdAt) <> (VersionRow.tupled, VersionRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      ((Rep.Some(id), Rep.Some(appVersion), createdAt)).shaped.<>(
        { r => import r._; _1.map(_ => VersionRow.tupled((_1.get, _2.get, _3))) },
        (_: Any) => throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)

    /** Database column app_version SqlType(varchar), Length(10,true) */
    val appVersion: Rep[String] = column[String]("app_version", O.Length(10, varying = true))

    /** Database column created_at SqlType(timestamptz) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at")

    /** Uniqueness Index over (appVersion) (database name version_unique) */
    val index1 = index("version_unique", appVersion, unique = true)
  }

  /** Collection-like TableQuery object for table Version */
  lazy val Version = new TableQuery(tag => new Version(tag))
}
