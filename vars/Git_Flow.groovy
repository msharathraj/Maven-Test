def call(String gitRepo, String gitProj) {
	pipeline {
		git url: "https://github.com/jenkinsci/${name}-plugin.git"
			git {
				remote {
					url("https://github.com/msharathraj/Maven-Test.git")
					branch('master')
					extensions {
					localBranch('master')
				}
			  }
			}
		stages {
			release =  getReleasedVersion()
			stage('Merge-Release'){
				bat "echo passed lib"
			}
		}	
		
		}
	
}
