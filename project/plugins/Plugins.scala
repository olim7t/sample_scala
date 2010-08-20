import sbt._

import java.net.URL

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val scalaToolsSnapshotRepo = "Scala-Tools Maven Repository" at "http://scala-tools.org/repo-snapshots"

	val formatter = "com.github.olim7t" % "sbt-scalariform" % "1.0.1-SNAPSHOT"
}
