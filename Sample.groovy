job('Test-Artifactory'){
	
	def server = Artifactory.server 'jenkins-artifactory-server' , username: 'admin', password: 'password'
	def rtMaven = Artifactory.newMavenBuild()
	rtMaven.resolver server: server, releaseRepo: 'sample-repo', snapshotRepo: 'sample-repo-snapshot'
	rtMaven.deployer server: server, releaseRepo: 'sample-repo-local', snapshotRepo: 'sample-repo-local'
	rtMaven.deployer.deployArtifacts = true
	
		parameters {
			choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
		}
		println "asd"
	
}

	
