pipelinejob('example-1') {
    deliveryPipelineConfiguration('qa')
    steps{
        deliveryPipelineConfiguration('qa1')
        
    }
}
