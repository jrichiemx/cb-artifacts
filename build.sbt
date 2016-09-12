name := """cb-artifacts"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.0"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.4.0"

libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.5.6"


