@Library('Maven-Test@test') _

job("Releae-Project") {
        description("This is release process job")
        label('master')
        keepDependencies(false)
        parameters {
            stringParam("PROJECT", "qe", "The project key specified for this project in GIT.")
            stringParam("REPOSITORY", "qe-pom", "The GIT branch to scan.")
            stringParam("SOURCE_BRANCH", "qe", "The GIT branch to checkout.")
        }
	scm {
            git("https://github.com/msharathraj/Maven-Test.git")
        }	
        
        disabled(false)
        concurrentBuild(false)
	steps{
        Git_Flow()
	}
        publishers {
            extendedEmail {
                triggers {
                    always {
                        subject("\$PROJECT_DEFAULT_SUBJECT")
                        content("\$PROJECT_DEFAULT_CONTENT")
                        attachmentPatterns()
                        attachBuildLog(false)
                        compressBuildLog(false)
                        contentType("project")
                        sendTo {
                          recipientList()
                        }
                    }
                }
                contentType("default")
                defaultSubject("\$DEFAULT_SUBJECT")
                defaultContent("\$DEFAULT_CONTENT")
                attachmentPatterns()
                preSendScript("\$DEFAULT_PRESEND_SCRIPT")
                attachBuildLog(false)
                compressBuildLog(false)
                replyToList()
                saveToWorkspace(false)
                disabled(false)
            }
        }
    }
