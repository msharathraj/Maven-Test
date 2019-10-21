pipelinejob() {
    deliveryPipelineConfiguration('qa')
    steps{
        deliveryPipelineConfiguration('qa1')
        
    }
}
