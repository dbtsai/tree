import sbt.Defaults.sbtPluginExtra

resolvers += Classpaths.typesafeResolver

resolvers += "Scala Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

// addSbtPlugin("com.alpinenow" % "junit_xml_listener" % "0.5.1")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

//addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

//addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.5")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
