#!/usr/bin/env groovy

@Library('Artifactory-Publish') _

pipeline{
	parameters {
		choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
		choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
		stringParam('RELEASE_BRANCH', '')
	}
		
	stage('checkout')
	{
		scm {
            git {
                remote {
                    url('https://github.com/cameronmcnz/rock-paper-scissors.git')
                }
                branch("*/master")
            }
        }	
	}	
	stage('Develop-Merge-Release'){
		steps{
		// This stage is going to merge the code from develop to release branch and runs maven to check build
			bat('''
			git branch -b release/${RELEASE_BRANCH}
			git checkout release
			echo "Merging the code from Develop Branch to Release Branch"
            git merge develop
			git push
			mvn package
			''')
		}
	}
	stage('Release-Merge-Master'){
		// This stage is going to merge the code from develop to release branch and runs maven to check build
		steps{
			bat('''
			git checkout master
			echo "Merging the code from Release Branch to Master Branch"
            git merge release/${RELEASE_BRANCH}
			git push
			mvn package
			git branch -d release/${RELEASE_BRANCH}
			''')
		}
	}
	stage('Release-to-artifactory'){
		//Git_Flow()
	}
}
