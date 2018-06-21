// Credential
String creds = readFileFromWorkspace('shared/credential/Creds.txt')

// Job Names
List<String> jobs = readFileFromWorkspace('job/job.txt').split(',')

for (String my_job : jobs) {
// Create job
multibranchPipelineJob("${my_job}") {
  displayName("${my_job}")
  branchSources {
    github {
      repoOwner("Juhibhadviya19")
      scanCredentialsId("${creds}")
      repository("hdx_devops")
      buildOriginPRMerge(true)
    }
  }
  orphanedItemStrategy {
    discardOldItems {
      daysToKeep(3)
    }
  }
}

}

