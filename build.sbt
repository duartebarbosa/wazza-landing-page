lazy val appName = "Wazza-landing-page"

lazy val appVersion = "2015.05"

scalaVersion := "2.11.6"

lazy val dependencies = Seq(
  jdbc,
  anorm,
  cache,
  ws,
  filters,
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.webjars" % "jquery" % "1.11.3",
  "org.webjars" % "font-awesome" % "4.3.0-1",
  "org.webjars" % "normalize.css" % "3.0.2",
  "org.webjars" % "webjars-play_2.10" % "2.3.0-3"  
)

libraryDependencies ++= dependencies

resolvers ++= Seq[Resolver](
    DefaultMavenRepository,
    Classpaths.typesafeReleases,
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    Classpaths.sbtPluginReleases,
    "Eclipse repositories" at "https://repo.eclipse.org/service/local/repositories/egit-releases/content/",
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )

lazy val mySettings = Seq("-unchecked", "-deprecation", "-feature", "-language:reflectiveCalls", "-language:postfixOps", "-optimize", "-Ywarn-adapted-args", "-Xlint", "-Xfatal-warnings")

// Root
lazy val home = Project(appName, file("."))
  .enablePlugins(play.PlayScala)
  .settings(scalacOptions ++= mySettings, version := appVersion, libraryDependencies ++= dependencies)
