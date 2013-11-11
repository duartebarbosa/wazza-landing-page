name := "landing-page"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.ecwid" % "ecwid-mailchimp" % "2.0.0.1"
)

play.Project.playScalaSettings
