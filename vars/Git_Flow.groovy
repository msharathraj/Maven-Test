def server
def buildInfo
def rtMaven
def descriptor

    
def call(){
	
	def server = Artifactory.server 'jenkins-artifactory-server'
	def descriptor = Artifactory.mavenDescriptor()
	descriptor.version = '1.2.0'
	descriptor.transform()
	def rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = 'MAVEN'
        rtMaven.deployer releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo-snapshot', server: server

        def buildInfo = Artifactory.newBuildInfo()
        rtMaven.run pom: 'pom.xml', goals: 'install package', buildInfo: buildInfo
        def uploadSpec = """{
	  "files": [
		{	
		  "pattern": "1.0*.jar",
		  "target": "sample-repo/"
		}
	 ]
	}"""
	server.upload spec: uploadSpec
        rtMaven.deployer.deployArtifacts = true // Disable artifacts deployment during Maven run
	rtMaven.deployer.deployArtifacts buildInfo
	server.publishBuildInfo buildInfo	
}
