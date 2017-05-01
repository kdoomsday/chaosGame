scalaVersion := "2.11.8"

name := "chaosGame"
organization := "com.ebarrientos"
version := "0.1"

resolvers += Resolver.bintrayRepo("underscoreio", "training")

fork := true
// coverageEnabled := true

lazy val compilecheck = taskKey[Unit]("compile and then scalastyle")

compilecheck in Compile := Def.sequential(compile in Compile, (scalastyle in Compile).toTask(""),
  (scapegoat in Compile)).value


import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value.setPreference(DoubleIndentClassDeclaration, true)

scapegoatVersion := "1.1.0"

wartremoverErrors ++= {
  import Wart._
  Seq(Any, Any2StringAdd, AsInstanceOf, EitherProjectionPartial, Enumeration, ExplicitImplicitTypes,
    FinalCaseClass, IsInstanceOf, ListOps, Nothing,
      // Null,
      Option2Iterable, OptionPartial, Product, Return,
    Serializable, TryPartial)
}

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",

  "underscoreio" %% "doodle" % "0.7.0"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-explaintypes",
  "-Ywarn-unused-import",
  "-encoding", "UTF-8",
  "-feature",
  "-Xlog-reflective-calls",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-unused",
  "-Ywarn-value-discard",
  "-Xlint",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Xfuture"
)

initialCommands := """
class Witness[T](val x: T)
object Witness{
  def apply[T](x: T): Witness[T] = new Witness(x)
}
"""

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.14")

import TodoListPlugin._
compileWithTodolistSettings
testWithTodolistSettings
