pipelineJob('job-dsl-artifactory-pipeline-example') {

    parameters {

        stringParam('SERVER_ID', SERVER_ID, 'Enter Artifactory server ID')

    }



    definition {

        cps {
           println 'asdad'
            script(readFileFromWorkspace('Maven-Test/Jenkinsfile'))
            sandbox()
            
        }

    }

}
