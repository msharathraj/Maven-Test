import hudson.plugins.*
import hudson.model.* 
import hudson.maven.*;
import hudson.tasks.*;
import org.jfrog.build.extractor.clientConfiguration.client.ArtifactoryBuildInfoClient;
import org.jfrog.hudson.action.ArtifactoryProjectAction;
import org.jfrog.hudson.maven2.ArtifactsDeployer;
import org.jfrog.hudson.maven2.MavenBuildInfoDeployer;
import org.jfrog.hudson.release.promotion.UnifiedPromoteBuildAction;

job('Test-Artifactory'){
	
	
		parameters {
			choiceParam('SOURCE_BRANCH', ['Master', 'Develop'], 'Source branch from code is merged to Destination')
			choiceParam('DESTINATION_BRANCH', ['Master', 'Release'], 'Destination branch where the code should be merged')
			choiceParam('TAG_REQUIRED', ['Yes', 'No'], 'Do you require a tag creation')
		}
		scm {
			git {
				remote {
					// This is used for getting the code checkout from the given repository
					url("https://github.com/cameronmcnz/rock-paper-scissors.git")
					branch('master')
					extensions {
					localBranch('master')
				}
			  }
			}
		}
	steps {
		println 'asd'	
	}
	
}

	
