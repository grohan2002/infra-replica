// Credential
String creds = readFileFromWorkspace('shared/credential/Creds.txt')

// Create snapshot job

pipelineJob("google-compute-snapshot") {
	description("Every day, this job creates snapshots in GCP of all persistent disks in every Kubernetes cluster in this GCP Project")
	keepDependencies(false)
	// The cron will run 7 AM UTC which is 12:00 am PST time
	triggers {
    	scm('0 7 * * *')
  	}
  		
	definition {
		cpsScm {
			scm {
				git {
					remote {
						github("Juhibhadviya19/snapshot-job", "https")
						credentials("${creds}")
					}
					branch("*/master")
				}
			}
			scriptPath("jenkins/google-compute-snapshot/Jenkinsfile")
		}
	}
	disabled(false)
	configure {
		it / 'properties' / 'com.coravy.hudson.plugins.github.GithubProjectProperty' {
			'projectUrl'('https://github.com/Juhibhadviya19/snapshot-job/')
			displayName()
		}
	}
}


// Create disk-usage job

freeStyleJob("hdx-jenkins-disk-space-monitor") {
	description("Every hour, checks how full the Jenkins disks are, sends warning to Slack if full.")
	keepDependencies(false)
	// The cron will run every hour on the hour.
	triggers {
    	scm('0 * * * *')
  	}

	definition {
		cpsScm {
			scm {
				git {
					remote {
						github("Juhibhadviya19/Jenkinsdiskspace", "https")
						credentials("${creds}")
					}
					branch("*/master")
				}
			}
			scriptPath("Jenkinsfile")
		}
	}
	disabled(false)
	configure {
		it / 'properties' / 'com.coravy.hudson.plugins.github.GithubProjectProperty' {
			'projectUrl'('https://github.com/Juhibhadviya19/Jenkinsdiskspace/')
			displayName()
		}
	}
}




