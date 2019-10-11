gitCreds = 'git'

organizationFolder('Test-Jobs') {
    description('This contains branch source jobs for Bitbucket')
    displayName('Test Containers')
    triggers {
        periodic(86400)
    }
    organizations {
	
		github {
          remote {
			url('https://github.com/msharathraj/Maven-Test.git')
			repoOwner('Maven-Test')
            branch("develop")
            extensions {
                localBranch('develop')
            }
          }
        }
    }
	
    configure { node ->
        // node represents <jenkins.branch.OrganizationFolder>
        def traits = node / 'navigators' / 'com.cloudbees.jenkins.plugins.bitbucket.BitbucketSCMNavigator' / 'traits'
        traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
            strategyId(3) // detect all branches
        }
    }
}

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

