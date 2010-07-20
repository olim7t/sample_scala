import sbt._
import FileUtilities.{write, Newline}
import com.github.olim7t.sbtscalariform._

class SampleProject(info: ProjectInfo) extends DefaultProject(info) with SxrConfig with ScalariformConfig

trait SxrConfig extends BasicScalaProject with MavenStyleScalaPaths with AutoCompilerPlugins {
  val sxr = compilerPlugin("org.scala-tools.sxr" %% "sxr" % "0.2.6")

  // Links to external sxr sources (provided as file:// URLs)
  def externalLinks: Seq[String] = Seq()

  override def compileOptions =
    CompileOption("-P:sxr:base-directory:" + mainScalaSourcePath.absolutePath) ::
    CompileOption("-P:sxr:output-formats:html+vim") ::
    super.compileOptions.toList

  override def testCompileOptions =
    CompileOption("-P:sxr:base-directory:" + testScalaSourcePath.absolutePath) ::
    CompileOption("-P:sxr:output-formats:html+vim") ::
    CompileOption("-P:sxr:link-file:" + linkFile.absolutePath) ::
    super.compileOptions.toList


  def linkFile = {
  	val linkFile = outputPath / "sxr.links"
  	if (!linkFile.exists) {
  	  val mainSxrPath = outputPath / (mainCompileDirectoryName + ".sxr")
  		val content = (externalLinks ++ Seq(mainSxrPath.asURL)).mkString(Newline)
  		write(linkFile.asFile, content, log)
    }
    linkFile
  }
}

// Alternative configuration to use a local build of sxr (not used here)
trait SxrLocalConfig extends BasicScalaProject with MavenStyleScalaPaths {
  val sxrPath = "/path/to/sxr_2.8.0-0.2.5.jar"

  override def compileOptions =
    CompileOption("-Xplugin:" + sxrPath) ::
    CompileOption("-P:sxr:base-directory:" + mainScalaSourcePath.absolutePath) ::
    CompileOption("-P:sxr:output-formats:vim") ::
    super.compileOptions.toList
}

trait ScalariformConfig extends BasicScalaProject with ScalariformPlugin {
	override def scalariformOptions = Seq(VerboseScalariform)
}
