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
    steps{
	conditionalSteps {
            condition {
				stringsMatch('Master', 'Master', false)
				batchFile('git branch false')
            }
			runner('Run')
            steps {
				batchFile('git branch')
				batchFile('echo Hello steps false! ' )
            }
        }
		conditionalSteps {
            condition {
				stringsMatch('Master', 'Master', true)
				batchFile('echo branch true')
            }
			runner('Run')
            steps {
				batchFile('git branch')
				batchFile('echo Hello steps true! ' )
            }
        }
		conditionalSteps {
            condition {
				stringsMatch("Master", "Master", true)
				batchFile('echo branch true')
            }
			runner('Run')
            steps {
				batchFile('git branch')
				batchFile('echo Hello steps true double! ' )
            }
        }
		conditionalSteps {
            condition {
				stringsMatch("Master", "Master", true)
				batchFile('echo branch true')
            }
			runner('Fail')
            steps {
				batchFile('git branch')
				batchFile('echo Hello steps true double! fail' )
            }
        }
	}	
	
}
