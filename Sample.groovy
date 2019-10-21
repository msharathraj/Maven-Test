pipelineJob('sadad-master') {

    definition {

        cps {
           println 'asdad'
            script(readFileFromWorkspace('Maven-Test/Jenkinsfile'))
            sandbox()
            
        }

    }

}
