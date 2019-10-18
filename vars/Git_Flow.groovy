def call(String gitRepo, String gitProj) {
	
	node {
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
	
	
	/*pipeline {
		parameters {
			choiceParam('SOURCE_BRANCH', ['Develop', 'Master'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
		}
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
				shell('''
					git branch
					git checkout release
					git merge develop
					git push
					mvn clean build
					
				''')
			}
			stage('Merge-Tag'){
				shell('''
					git tag -a ${release} -m "New version ${release} 
					git push origin ${release}
					git checkout ${release}
					git merge develop
					git push
					mvn clean build
				''')
			}
			stage('Merge-Master'){
				shell('''
					git branch
					git checkout master
					git merge develop
					git push
					mvn clean build
				''')
			}
			/*stage('Release-to-generating'){
				batchFile('mvn -Drevision='${release}' clean deploy')
			}*/
		}
	} */
}
