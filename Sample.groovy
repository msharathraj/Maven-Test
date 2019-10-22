@Library('Maven-Test@test') _
pipeline {  
    	
	stage('checkout')
	{
	    git url: 'https://github.com/cameronmcnz/rock-paper-scissors.git' 
		Git_Flow()
        	
	}	
	
}
