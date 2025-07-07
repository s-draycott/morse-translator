ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test


lazy val root = (project in file("."))
  .settings(
    name := "morse-translator",
    idePackagePrefix := Some("com.sdraycott.morsetranslator")
  )
