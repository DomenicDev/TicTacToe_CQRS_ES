ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.cassisi"
ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "TicTacToe"
  )

// SLF4J LOGGING
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.36"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"