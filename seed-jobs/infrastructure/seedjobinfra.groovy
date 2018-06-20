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
