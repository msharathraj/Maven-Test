def call(String gitRepo, String gitProj) {
	def pomData =  readFileFromWorkspace('pom.xml')
	pipeline {
		parameters {
			choiceParam('SOURCE_BRANCH', ['Develop', 'Master'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
		}
		scm {
			git {
				remote {
					url("ssh://git@stash.intralinks.com:7999/${gitProj}/${gitRepo}.git")
					branch('master')
					extensions {
					localBranch('master')
				}
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
			stage('Release-to-generating'){
				batchFile('mvn -Drevision='${release}' clean deploy')
			}
		}
	}
}
def getReleasedVersion() {
	return (readFileFromWorkspace('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}