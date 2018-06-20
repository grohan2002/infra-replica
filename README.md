1. There are 3 folders:
    A. Repo
    B. Seed-jobs
    C. Shared

2. Repo has repo.txt with all the repository names ( whose Multipipeline build jobs are to be created)

3. Shared->credential has Creds.txt that has Credential ID to login to GitHub.

4. Seed-jobs contain a groovy script that reads repo.txt and Creds.txt, and creates MultiPipeline Build jobs for all the repos.
