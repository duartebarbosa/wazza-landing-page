lazy val appName = "landing-page"

lazy val appVersion = "1.0-SNAPSHOT"

scalaVersion := "2.10.4"

lazy val dependencies = Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"
)

libraryDependencies ++= dependencies

// Root
lazy val home = Project(appName, file("."))
  .enablePlugins(play.PlayScala)
  .settings(version := appVersion, libraryDependencies ++= dependencies)
