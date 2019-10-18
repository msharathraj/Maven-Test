def call(String gitRepo, String gitProj) {
	node{
	stage('Merge-Release'){
		bat 'echo passed lib release'
	}
	}
}
static def artifactReleaseProcesstest(){
	
node{
	stage('Merge-Release-arti'){
		bat 'echo passed lib release arti'
	}
	}
}
static def artifactReleaseProcesstest1(String s){
	
node{
	stage('Merge-Release-arti-s'){
		bat 'echo passed lib release arti-s'
	}
	}
}
