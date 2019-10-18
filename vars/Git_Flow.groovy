def call() {
	node {
		pipeline {
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
}
