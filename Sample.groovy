job('test'){
node('master') {
  withMaven(maven:'M3') {
    stage('Checkout') {
      git url: 'https://github.com/piomin/sample-spring-cloud-contract-ci.git', credentialsId: 'piomin-github', branch: 'master'
    }
    stage('Publish') {
      def server = Artifactory.server 'artifactory'
      def rtMaven = Artifactory.newMavenBuild()
      rtMaven.tool = 'M3'
      rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
      rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
      rtMaven.deployer.artifactDeploymentPatterns.addInclude("*stubs*")
      def buildInfo = rtMaven.run pom: 'person-service/pom.xml', goals: 'clean install'
      rtMaven.deployer.deployArtifacts buildInfo
      server.publishBuildInfo buildInfo
    }
  }
}
}
