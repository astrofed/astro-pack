
import sbt.Keys._
import sbt._

object BuildSettings {

  val scalacBuildOptions = Seq("-unchecked", "-deprecation", "-feature", "-Xlint:-infer-any")

  lazy val commonSettings = Seq(
    scalacOptions := scalacBuildOptions,
    resolvers ++= Seq(
      "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
      "sonatype.repo" at "https://oss.sonatype.org/content/repositories/public/",
      "apache.repo" at "https://repository.apache.org/content/repositories/snapshots/",
      "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/",
      "confluent" at "http://packages.confluent.io/maven/"
    )
  )

  def projSettings(dependencies: Seq[ModuleID] = Seq()) = {
    commonSettings ++ Seq(
      libraryDependencies ++= dependencies
    )
  }
}