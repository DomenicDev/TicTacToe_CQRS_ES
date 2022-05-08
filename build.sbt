ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "de.cassisi"
ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "TicTacToe"
  )

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"