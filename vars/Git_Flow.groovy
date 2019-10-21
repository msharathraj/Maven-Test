def server
def buildInfo
def rtMaven
    
def call(){
		
		server = Artifactory.server 'jenkins-artifactory-server'

        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = 'MAVEN'// Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo-snapshot', server: server
        rtMaven.resolver releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo-snapshot', server: server
        rtMaven.deployer.deployArtifacts = false // Disable artifacts deployment during Maven run

        buildInfo = Artifactory.newBuildInfo()
		
		rtMaven.run pom: 'pom.xml', goals: 'install', buildInfo: buildInfo
		
		rtMaven.deployer.deployArtifacts buildInfo
		
		server.publishBuildInfo buildInfo	

}
