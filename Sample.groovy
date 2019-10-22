@Library('Maven-Test@test') _
pipeline {  
    	agent any
	stages{
	stage('checkout')
	{
		steps{
	    git url: 'https://github.com/cameronmcnz/rock-paper-scissors.git' 
		Git_Flow()
		}
	}	
	}
}
