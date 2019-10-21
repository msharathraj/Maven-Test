def server
def buildInfo
def rtMaven
    
def call(){
	
	rtServer (id: "jenkins-artifactory-server",  url: 'http://localhost:8081/artifactory',
                    username: 'admin',
					password: 'password'
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
                    serverId: "jenkins-artifactory-server",
                    releaseRepo: "sample-repo",
                    snapshotRepo: "sample-repo"
                )
				rtUpload(
					serverId: "jenkins-artifactory-server",
					pattern: "*roshambo*.jar",
					target: "/sample-repo/"
				)
				rtPublishBuildInfo(
					serverId: "jenkins-artifactory-server",
				)	
	
}
