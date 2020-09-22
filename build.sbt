import scala.language.postfixOps
import scala.reflect.io.Directory

name := """freewill"""
organization := "dev.pknn"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.2"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.16"
libraryDependencies += "com.github.tminglei" %% "slick-pg" % "0.19.2"

lazy val cleanPersist =
  taskKey[Unit]("Clean Persist Layer generated with generatePersist")

cleanPersist := {
  val dir = (scalaSource in Compile) value

  val directory =
    new Directory(dir / "persists" / "generated")

  directory.deleteRecursively()

  println("Persists Layer cleaned.")
}

lazy val generatePersist =
  taskKey[Unit]("Generate Persist Layer with slick-codegen")

generatePersist := {
  val slickDriver = "slick.jdbc.PostgresProfile"
  val jdbcDriver = "org.postgresql.Driver"

  val dbHost = sys.env.getOrElse("DB_HOST", "localhost")
  val dbPort = sys.env.getOrElse("DB_PORT", "5432")
  val dbName = sys.env.getOrElse("DB_NAME", "freewill")

  val dbUrl = s"jdbc:postgresql://$dbHost:$dbPort/$dbName"

  val dbUser = sys.env.getOrElse("DB_USER", sys.env("USER"))
  val dbPassword = sys.env.getOrElse("DB_PASSWORD", "")

  val outputDir = (scalaSource in Compile) value
  val pkg = "persists.generated"

  val classpath = (dependencyClasspath in Compile) value
  val taskStreams = streams value

  // Clean older persists before generate new one.
  cleanPersist.value

  runner.value
    .run(
      "slick.codegen.SourceCodeGenerator",
      classpath.files,
      Array(
        slickDriver,
        jdbcDriver,
        dbUrl,
        outputDir.getPath,
        pkg,
        dbUser,
        dbPassword
      ),
      taskStreams.log
    )
    .failed foreach (sys error _.getMessage)
  println("Persists Layer Generated")
}

dockerUsername := Some(sys.props("dockerUsername"))
