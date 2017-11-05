#!Groovy

scm_url = ''
scm_creds = ''

node(){
    stage 'Checkout'
        git branch: env.BRANCH_NAME, credentialsId: scm_creds, url: scm_url

        sh 'printenv'
}
