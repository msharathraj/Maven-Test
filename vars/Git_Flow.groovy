def call(String gitRepo, String gitProj) {
	node{
	stage('Release-to-artifactory'){
				steps {
					rtServer (
						//id: "jenkins-artifactory-server",
						//url: 'http://mvn.ilnxqcdev.com:9955/artifactory',
						//Need to add credentials
						//username: 'admin',
						//password: 'password'
					)
				}
	}
}
}
