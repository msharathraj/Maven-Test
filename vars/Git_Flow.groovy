def server
def buildInfo
def rtMaven
    
def call(){
	
	server = Artifactory.newServer url: 'http://localhost:8081/artifactory', username: 'admin', password: 'password'
	rtMaven = Artifactory.newMavenBuild()
	rtMaven.resolver server: server, releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo'
	rtMaven.deployer server: server, releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo'
	rtMaven.tool = 'MAVEN'
	
	def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean install'
	rtMaven.run pom: 'maven-example/pom.xml', goals: 'clean install', buildInfo: existingBuildInfo
	
	rtMaven.deployer.deployArtifacts buildInfo
	server.publishBuildInfo buildInfo
	
}
