def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat 'echo passed lib release'
	}
	}
}
static def artifactReleaseProcesstest(){
	
		bat 'echo passed lib release arti'
	
}
static def artifactReleaseProcesstest1(String s){
	
		bat 'echo passed lib release arti-s'
	
}
