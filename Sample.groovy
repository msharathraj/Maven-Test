job("Merge-Release-Test") {
	def pomData =  readFileFromWorkspace('pom.xml')
	parameters {
		choiceParam('SOURCE_BRANCH', ['Develop', 'Master'], 'Source branch from code is merged to Destination')
		choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
		choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
	}
     scm {
        git {
          remote {
			url('https://github.com/msharathraj/Maven-Test.git')
            branch('develop')
            extensions {
                localBranch('develop')
            }
          }
        }
    }
    steps {
		 release =  getReleasedVersion()
	     batchFile("echo Hello World!  ${release} ")
	     batchFile('echo DESTINATION_BRANCH ${DESTINATION_BRANCH}! ')
		 batchFile('git branch')
		 batchFile('git checkout master')
		 batchFile('git merge origin/test')
		batchFile('echo Hello Merge! ' )
	     
		 /*conditionalSteps {
            condition {
				stringsMatch("${DESTINATION_BRANCH}", 'Master', true)
            }
			runner('Run')
            steps {
				batchFile('git branch')
				batchFile('git checkout ${DESTINATION_BRANCH}')
				batchFile('git merge ${SOURCE_BRANCH}')
				batchFile('echo Hello Merge! ' )
            }
        }
			 
		 conditionalSteps {
            condition {
                stringsMatch("${TAG_REQUIRED}", 'Yes', false)
            }
            steps {
				
                batchFile('git tag -a ${release} -m "New version ${release} " ')
				batchFile('git push origin ${release}')
				batchFile('git checkout ${release}')
				batchFile('git merge master')
				batchFile('echo Hello Tag! ' )
            }
        } */
		
		triggers {
			bitbucketPush()
		}
		
		maven {
            goals('clean install')
        }
		/*resolveArtifacts {
            failOnError()
            snapshotUpdatePolicy('always')
            targetDirectory('lib')
            artifact {
                groupId('org.slf4j')
                artifactId('slf4j-api')
                version('[1.7.5,1.7.6]')
            }
            artifact {
                groupId('ch.qos.logback')
                artifactId('logback-classic')
                version('1.1.1')
                classifier('sources')
            }
        } */
	 }
}
def getReleasedVersion() {
	return (readFileFromWorkspace('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}