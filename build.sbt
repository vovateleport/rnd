lazy val scalaV = "2.11.8"
scalaJSUseRhino in Global := false

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  scalaVersion := scalaV
)

lazy val `rnd-portal` = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "rnd-portal",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "com.github.japgolly.scalajs-react" %%% "core" % "0.11.1",
      "com.github.japgolly.scalajs-react" %%% "extra" % "0.11.1",
      "com.github.japgolly.scalajs-react" %%% "ext-scalaz72" % "0.11.1",
      "com.github.japgolly.scalajs-react" %%% "ext-monocle" % "0.11.1"
    ),

    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % "15.0.2"
        /        "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "15.0.2"
        /         "react-dom.js"
        minified  "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM",

      "org.webjars.bower" % "react" % "15.0.2"
        /         "react-dom-server.js"
        minified  "react-dom-server.min.js"
        dependsOn "react-dom.js"
        commonJSName "ReactDOMServer"
    ),
    skip in packageJSDependencies := false
  )