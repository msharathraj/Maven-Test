job("Merge-Release-Git") {
	def pomData =  readFileFromWorkspace('pom.xml')
	parameters {
		choiceParam('SOURCE_BRANCH', ['Develop', 'Master'], 'Source branch from code is merged to Destination')
		choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
		choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
	}
     scm {
        git {
          remote {
			url('https://github.com/msharathraj/SampleTest.git')
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
	     
	     batchFile('echo Hello World! ' )
	     batchFile('git branch')
		 
		 /*if(${TAG_REQUIRED}){
			batchFile('git tag -a ${release} -m "New version 1.4" ')
			batchFile('git push origin ${release}')
			batchFile('git checkout ${release}')
			batchFile('git merge master')
		 }*/
	     triggers {
			bitbucketPush()
		}
		maven {
            //goals('clean')
            //goals('verify')
            properties(skipTests: true)
            //mavenInstallation('Maven 3.1.1')
            //providedSettings('central-mirror')
        }
		resolveArtifacts {
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
        }
	 }
}
def getReleasedVersion() {
	return (readFileFromWorkspace('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}