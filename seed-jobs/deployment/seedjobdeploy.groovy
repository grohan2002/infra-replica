// Credential
String creds = readFileFromWorkspace('shared/credential/Creds.txt')

// Repos
List<String> repos = readFileFromWorkspace('repo/repo.txt').split(',')

for (String my_repo : repos) {
// Create job
multibranchPipelineJob("${my_repo}") {
  displayName("${my_repo}")
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

