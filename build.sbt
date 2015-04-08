name := "Paxos"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.9" % "test",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.9",
  "ch.qos.logback" % "logback-classic" % "1.0.13" % "runtime",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")