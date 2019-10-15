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
		/*batchFile('git tag -a ${release} -m "New version ${release} " ')
		batchFile('git push origin ${release}')
		batchFile('git checkout ${release}')
		batchFile('git merge develop')
		//batchFile('mvn build' )
		
		batchFile('echo Hello Tag! ' )
		
		batchFile('git checkout release')
		batchFile('git merge develop')
		
		
	
	     batchFile("echo Hello World!  ${release} ")
	     batchFile('echo DESTINATION_BRANCH ${DESTINATION_BRANCH}! ')
		 batchFile('git branch')
		 batchFile('git checkout master')
		 batchFile('git merge origin/test')
		 batchFile('echo Hello Merge! ' )
		 batchFile("def mvnhome = tool name: 'MAVEN', type: 'maven'")
		 
		 batchFile( "echo ${mvnhome}/bin/mvn install")*/
		 
		script{
			def logs = currentBuild.result
			if(logs == "SUCCESS")
		}
		
		triggers {
			bitbucketPush()
		}
		
		maven {
            goals('clean install deploy')
        }
	 }
}
def getReleasedVersion() {
	return (readFileFromWorkspace('pom.xml') =~ '<version>(.+)-SNAPSHOT</version>')[0][1]
}

def test(){
		def jenkins = Jenkins.getInstance()
		def jobName = "myJob"
		def job = jenkins.getItem(jobName)

		println "Job type: ${job.getClass()}"
		println "Is building: ${job.isBuilding()}"
		println "Is in queue: ${job.isInQueue()}"
		println "Last successfull build: ${job.getLastSuccessfulBuild()}"
		println "Last failed build: ${job.getLastFailedBuild()}"
		println "Last build: ${job.getLastBuild()}"
		println "All builds: ${job.getBuilds().collect{ it.getNumber()}}"
}