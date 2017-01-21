import sbt.Keys._
import Dependencies._
import BuildSettings._

name := "astro-pack"

scalaVersion in ThisBuild := "2.11.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-optimize",
  "-Xfatal-warnings",
  "-Xlint"
)

lazy val fits2ancii = (project in file("fits2ancii"))
  .settings(projSettings(common ++ astro ++ gui ++ akka))

lazy val `dibs-extractor` = (project in file("dibs-extractor"))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    mainClass in assembly := Some("edu.spectrum.DIBsExtractor"),
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "edu.spectrum",
    projSettings(common ++ astro ++ gui)
  )

val runAll = inputKey[Unit]("Runs all subprojects")

runAll := {
  (run in Compile in fits2ancii).evaluated
  (run in Compile in `dibs-extractor`).evaluated
}

fork in run := true
    