import sbt._

class ScalaQuerySiteProject(info: ProjectInfo) extends DefaultProject(info)
{
  val fmppDep = "net.sourceforge.fmpp" % "fmpp" % "0.9.13" % "fmpp"
  val fmppConf = config("fmpp") hide

  lazy val fmpp =
    runTask(Some("fmpp.tools.CommandLine"), configurationClasspath(fmppConf), Nil)

  lazy val fmppClean = task { FileUtilities.clean("target" / "site", log) }

  override def compileAction = super.compileAction dependsOn(fmpp)
  override def cleanAction = super.cleanAction dependsOn fmppClean
}
