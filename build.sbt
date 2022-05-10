import sbt.Keys.libraryDependencies


ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.cassisi"
ThisBuild / scalaVersion := "2.13.8"


lazy val domain = (project in file("domain"))
  .settings(
    name := "domain",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-simple" % "1.7.36",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"
    )
  )

lazy val service = project.dependsOn(domain)
  .settings(
    name := "service"
  )


lazy val adapter = project.dependsOn(service)
  .settings(
    name := "adapter"
  )


lazy val projection = (project in file("projection"))
  .dependsOn(adapter)
  .settings(
    name := "projection"
  )

lazy val app_main = (project in file("app_main"))
  .dependsOn(adapter, projection)
  .settings(
    name := "app_main"
  )
