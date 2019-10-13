job("Merge-Release-Git") {
     scm {
        git {
          remote {
			url('https://github.com/msharathraj/SampleTest.git')
            branch("develop")
            extensions {
                localBranch('develop')
            }
          }
        }
    }
    steps {
		 release =  getReleasedVersion()
	     batchFile('echo Hello World! ${release}')
	     batchFile('echo Hello World! ' )
	     batchFile('git branch')
	     triggers {
			bitbucketPush()
		}
		 mavenJob('mvn clean install') {
			postBuildSteps('SUCCESS') {
				batchFile("echo 'run after Maven'")
			}
		}
	 }
}
def getReleasedVersion() {
    return (readFile("./pom.xml") =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}