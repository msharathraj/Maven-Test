import org.jfrog.hudson.*;

def server = Artifactory.server 'jenkins-artifactory-server' , username: 'admin', password: 'password'
def rtMaven = Artifactory.newMavenBuild() 
   

job("job-dsl-artifactory-freestyle-maven-example") {
    
}
