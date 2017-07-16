name := "RecipeApp"

version := "1.0"

scalaVersion := "2.12.1"



//
// This puts the name of the project into the sbt command prompt. Very useful when working with multiple projects
//
shellPrompt in ThisBuild := { state => "\033[0;36m" + Project.extract(state).currentRef.project + "\033[0m] " }


libraryDependencies ++= Seq(
	"org.slf4j" 	 % "slf4j-api" 			% "1.7.10",
	"ch.qos.logback" % "logback-classic" 	% "1.1.2",
	"org.specs2" 	 % "specs2-core_2.12"   % "3.9.2" % "test"
)

