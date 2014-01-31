name := "landing-page"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.typesafe.slick" %% "slick" % "1.0.0",
  "org.postgresql" % "postgresql" % "9.2-1004-jdbc4"
)

play.Project.playScalaSettings
