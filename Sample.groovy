multibranchPipelineJob('example') {
    branchSources {
        git {
            remote('https://github.com/msharathraj/SampleTest.git')
            //credentialsId('github-ci')
            includes('JENKINS-*')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}