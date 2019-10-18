def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat "echo passed lib release"
	}
	}
}
def artifactReleaseProcess(String s){
	
	stage('Merge-Release-lib'){
		bat "echo passed lib release"
	}	

}
