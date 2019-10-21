
@Library('Maven-Test@test') _

pipeline {
	agent any
	tools{
		maven 'MAVEN'
	}
    stages {
        stage ('Clone') {
            steps {
                git branch: 'dragon', url: "https://github.com/cameronmcnz/rock-paper-scissors.git"
				bat 'git checkout master'
				bat 'mvn install'
            }
        }
		
        stage ('Artifactory configuration') {
            steps {
                
                Git_Flow()
                
                }
        }
		
        

        
    }
}
