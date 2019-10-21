@Library('Maven-Test@test') _
node {  
    parameters {
		choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
		choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
		stringParam('RELEASE_BRANCH', '')
	}
		
	stage('checkout')
	{
	    git url 'https://github.com/cameronmcnz/rock-paper-scissors.git' branch "master"
        	
	}	
	stage('Develop-Merge-Release'){
		steps{
		// This stage is going to merge the code from develop to release branch and runs maven to check build
			bat('''
			git branch 
			echo "Merging the code from Develop Branch to Release Branch"
            mvn install
			''')
		}
	}
	stage('Release-Merge-Master'){
		// This stage is going to merge the code from develop to release branch and runs maven to check build
			bat('''
			git branch 
			echo "Merging the code from Master Branch to Release Branch"
            mvn install
			
			''')
		
	}
	stage('Release-to-artifactory'){
		Git_Flow()
	}
}
