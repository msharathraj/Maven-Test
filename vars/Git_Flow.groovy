def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat "echo passed lib release"
	}
	}
}
def artifactReleaseProcess(String ss){
	
	bat "echo passed lib release"
		

}
