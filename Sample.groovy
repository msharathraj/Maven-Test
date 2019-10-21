pipeline
 {
		agent any
		parameters {
			choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			stringParam('RELEASE_BRANCH', '')
		}
		
		
			
		stages {
        stage('Build') { 
            steps{	
				git  url : 'https://stash.intralinks.com/scm/qe/qe-pom.git', credentialsId: 'git'
			}	
        }
        stage('Test'){
            steps {
                println 'test'
            }
        }
        stage('Deploy') {
            
        }
    }
		
		
	}
