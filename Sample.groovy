#!/usr/bin/env groovy

try{
node('sadasd-1'){
    stages{
        stage('test'){
            steps{
             bat 'echo sample test'
            }
        }  
    }    
}
}catch (Exception e){
    
} 
