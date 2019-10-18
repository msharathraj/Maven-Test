def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat "echo passed lib release"
	}
	}
}
def artifactReleaseProcess(){
node{
	
	stage('Merge-Release-lib'){
		bat "echo passed lib release"
	}	
}
}
