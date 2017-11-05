#!Groovy

scm_branch = ''
scm_creds = ''
scm_url = ''

node() {
    stage 'Checkout'
        git branch: scm_branch, credentialsId: scm_creds, url: scm_url

    stage 'Shell Stage'
        sh '''#!/bin/bash -l
            echo $0

            whoami
            pwd
            printenv
            '''
}
