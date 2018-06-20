1. There are 3 folders:
    A. Job -> job.txt
    B. Seed-jobs
       1. Deployment -> seedjobdeploy.groovy
       2. Infrastrucure -> seedjobinfra.groovy
    C. Shared -> Creds.txt
    D. job-config -> config.xml

2. Job has job.txt with all the build job names for Multipipeline build jobs that are to be created.

3. Shared->credential has Creds.txt that has Credential ID to login to GitHub.

4. Seed-jobs contain:
   1. seedjobdeploy.groovy that reads job.txt and Creds.txt, and creates MultiPipeline Build jobs for all the job names in job.txt.
   2. seedjobinfra.groovy creates infrastructure related build jobs ( currently it creates google-compute-snapshot build job)

5. job-config has config.xml that contains the configuration of the seed job (that will execute the DSL scripts, namely, seedjobdeploy.groovy and seedjobinfra.groovy)
The seed job can be created through command line using:

java -jar jenkins-cli.jar -s http://jenkins_url/ create-job hdx-seed-job < config.xml

