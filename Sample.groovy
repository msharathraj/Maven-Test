job("job-dsl-artifactory-freestyle-maven-example") {
    scm {
        git("https://github.com/JFrog/project-examples.git", "master")
    }

    configure { node ->
        // Configure the Maven builder
        node / 'builders' << 'org.jfrog.hudson.maven3.Maven3Builder' {
            goals 'clean install' // Specifies the goals to execute, such as "clean install" or "deploy"
        }

        // Configure artifactory maven job
        node / 'buildWrappers' << 'org.jfrog.hudson.maven3.ArtifactoryMaven3Configurator' {
            // === Resolver ===
            enableResolveArtifacts true
            resolverDetails {
                artifactoryName 'jenkins-artifactory-server'
                resolveReleaseRepository {
                    keyFromText 'example-repo'
                }
                resolveSnapshotRepository {
                    keyFromText 'example-repo-snapshot'
                }
            }
            // Optional - Override resolver credentials
            //resolverCredentialsConfig {
            //    credentialsId ARTIFACTORY_CREDENTIALS
            //}

            // === Deployer ===
            deployerDetails {
                artifactoryName 'jenkins-artifactory-server'
                deployReleaseRepository {
                    keyFromText 'example-repo'
                }
                deploySnapshotRepository {
                    keyFromText 'example-repo'
                }
            }
            // Optional - Override deployer credentials
            //deployerCredentialsConfig {
            //    credentialsId ARTIFACTORY_CREDENTIALS
            //}

            // === Override build name ===
            overrideBuildName false // Check if you wish to override Artifactory default build name.
            customBuildName '' // Sets the new Artifactory build name.

            // === Deploy artifacts to Artifactory ===
            deployArtifacts true
            artifactDeploymentPatterns {
                // Comma or space-separated list of Ant-style patterns of files that will be included in publishing. Include/Exclude patterns are applied on the published file path before any exclude patterns.
                includePatterns 'p1=v1;p2=v2'
                excludePatterns 'p3=v3;p4=v4'
            }

            // === Filter excluded artifacts from build Info ===
            filterExcludedArtifactsFromBuild true //  Add the excluded files to the excludedArtifacts list and remove them from the artifacts list in the build info.

            // === Capture and publish build info ===
            deployBuildInfo true // Check if you wish to publish build information to Artifactory.
            deploymentProperties 'buildStatus=RC;platforms=win386,win64,osx,debian' //  Semicolon-separated list of properties to attach to all deployed artifacts in addition to the default ones (build.name, build.number, vcs.revision, etc.). Property values can take environment variables.
            includeEnvVars true // Check if you wish to include all environment variables accessible by the builds process.
            envVarsPatterns {
                // Comma or space-separated list of environment variables that will be included as part of the published build info.
                // Environment variables may contain the * and the ? wildcards. Include patterns are applied before any exclude patterns.
                includePatterns ''
                excludePatterns '*password*,*secret*,*key*'
            }

            recordAllDependencies false // Check if you wish build information published to Artifactory to include implicit project dependencies

        }
    }
}
