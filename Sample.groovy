gitCreds = 'git'

listView('Git-Flow-Jobs') {
	description("All Git Flow Jobs.")
    filterBuildQueue(true)
    filterExecutors(true)
    jobs {
        regex('.*-Git')
    }
    columns {
        status()
     	weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}


multibranchPipelineJob("Test") {
    // configure the branch / PR sources
    branchSources {
      branchSource {
        source {
          git {
            //credentialsId("top-secret-1234-some-guid")
            //repoOwner("${bitbucket_project.toUpperCase()}")
            //repository("${bitbucket_repo}")
            remote("https://github.com/msharathraj/Maven-Test.git")
            traits {
              headWildcardFilter {
                includes("master release/* feature/* bugfix/*")
                excludes("")
              }
            }
          }
        }
        strategy {
          defaultBranchPropertyStrategy {
            props {
              // keep only the last 10 builds
              buildRetentionBranchProperty {
                buildDiscarder {
                  
                }
              }
            }
          }
        }
      }
    }
    // discover Branches (workaround due to JENKINS-46202)
    configure {
      def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
      traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
        strategyId(3) // detect all branches
      }
    }

    // check every minute for scm changes as well as new / deleted branches
    triggers {
      periodic(1)
    }
    // don't keep build jobs for deleted branches
    orphanedItemStrategy {
      discardOldItems {
        numToKeep(-1)
      }
    }
  }




