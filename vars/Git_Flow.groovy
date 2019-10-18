def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat "echo passed lib release"
	}
	}
}
def artifactReleaseProcesstest(){
	
	bat "echo passed lib release"
		

}
