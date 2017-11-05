#!Groovy

scm_creds = ''
scm_url = ''
scm_branch = ''

container_org = ''
container_name = ''
container_tag = ''

node() {
    stage 'Pull Latest Container'
        def active_container = docker.image("${container_org}/${container_name}:${container_tag}");

        // pull to get the latest version. slaves may be stale.
        active_container.pull()

    stage 'Do Things Inside Container'
        active_container.inside {
            git branch: scm_branch, credentialsId: scm_creds, url: scm_url

            sh 'printenv'
        }
}
