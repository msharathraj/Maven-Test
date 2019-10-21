def server
def buildInfo
def rtMaven
    
def call(String depReleaseRepo,String depSnapshot,String resReleaseRepo,String resSnapshot){
		
	rtServer (id: "jenkins-artifactory-server",  url: 'http://localhost:8081/artifactory',
				username: 'admin', password: 'password'
             )
    	rtMavenDeployer (
	    id: "deployer-unique-id",
            serverId: "jenkins-artifactory-server",
            releaseRepo: "sample-repo",
            snapshotRepo: "sample-repo",
            deployArtifacts:"true"
            )
        rtMavenResolver (
            id: "resolver-unique-id",
            serverId: "jenkins-artifactory-server",D
            releaseRepo: "sample-repo",
            snapshotRepo: "sample-repo"
           )
		   
	rtMavenRun (
		tool: 'MAVEN', 
		pom: 'pom.xml',
		goals: 'clean install',
		deployerId: "deployer-unique-id",
		resolverId: "resolver-unique-id"
		)		
	rtUpload(
		serverId: "jenkins-artifactory-server",
     		target: "/sample-repo/"
		)
	rtPublishBuildInfo(
		serverId: "jenkins-artifactory-server",
	)
	
}
