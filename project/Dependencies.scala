import sbt._

//@formatter:off
object Dependencies {

  val akka = {
    val akkaVersion = "2.4.14"

    Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      // test scope
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
    )
  }

  val monocle = {
    val monocleVersion = "1.2.1"

    Seq(
      "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-state" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-refined" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test"
    )
  }

  val common = Seq(
    "com.chuusai" %% "shapeless" % "2.3.2",
    "joda-time" % "joda-time" % "2.9.6",
    "org.joda" % "joda-convert" % "1.8",
    compilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)
  )

  val gui = Seq(
    "com.miglayout" % "miglayout-swing" % "5.0",
    "commons-io" % "commons-io" % "2.4",
    "org.apache.commons" % "commons-math3" % "3.6.1",
    "org.jfree" % "jcommon" % "1.0.23",
    "org.jfree" % "jfreechart" % "1.0.19"
  )

  val astro = Seq(
    "gov.nasa.gsfc.heasarc" % "nom-tam-fits" % "1.15.1"
  )

}

// /@formatter:on
