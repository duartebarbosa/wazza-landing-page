lazy val appName = "Wazza-landing-page"

lazy val appVersion = "2015.02"

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

lazy val mySettings = Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls", "-language:postfixOps", "-optimize")

// Root
lazy val home = Project(appName, file("."))
  .enablePlugins(play.PlayScala)
  .settings(scalacOptions ++= mySettings, version := appVersion, libraryDependencies ++= dependencies)
