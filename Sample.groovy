#!/usr/bin/env groovy

//@Library('Artifactory-Publish') _

job('sada')
 {
		parameters {
			choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			stringParam('RELEASE_BRANCH', '')
		}
		
		stage('checkout')
		{
			steps{	
				git  url : 'https://stash.intralinks.com/scm/qe/qe-pom.git', credentialsId: 'git'
			}	
		}	
			stage('Develop-Merge-Release'){
			steps{
			// This stage is going to merge the code from develop to release branch and runs maven to check build
				println 'ads'
				}
			}
			stage('Release-Merge-Master'){
			// This stage is going to merge the code from develop to release branch and runs maven to check build
			steps{
					printlin 'merge'
				
				}
			}
			
			stage('Release-to-artifactory'){
				//Git_Flow()
			}
		
	}
