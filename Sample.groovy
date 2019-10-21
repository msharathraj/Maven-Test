@Library('Maven-Test@test') _
node {  
    parameters {
		choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
		choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
		stringParam('RELEASE_BRANCH', '')
	}
		
	stage('checkout')
	{
	    git url: 'https://github.com/cameronmcnz/rock-paper-scissors.git' 
        	
	}	
	
}
