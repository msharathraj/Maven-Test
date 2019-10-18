def call(String gitRepo, String gitProj) {
	
	stage('Merge-Release'){
		bat "echo passed lib release"
	}
	
}
def artifactReleaseProcess(){
	stage('Merge-Release-lib'){
		bat "echo passed lib release"
	}	

}
