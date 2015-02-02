lazy val appName = "Wazza-landing-page"

lazy val appVersion = "2015.02"

scalaVersion := "2.10.4"

lazy val dependencies = Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.webjars" % "bootstrap" % "3.3.2",
  "org.webjars" % "jquery" % "1.11.2",
  "org.webjars" % "font-awesome" % "4.3.0-1",
  "org.webjars" % "normalize.css" % "3.0.2",
  "org.webjars" % "webjars-play_2.10" % "2.3.0-2",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"
)

libraryDependencies ++= dependencies

resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

lazy val mySettings = Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls", "-language:postfixOps", "-optimize")

// Root
lazy val home = Project(appName, file("."))
  .enablePlugins(play.PlayScala)
  .settings(scalacOptions ++= mySettings, version := appVersion, libraryDependencies ++= dependencies)
