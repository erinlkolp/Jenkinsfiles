#!Groovy

bb_creds = ''
env.bitbucket_url = ''

node(){
    stage 'Send Result'
        bbnotify('INPROGRESS')
        bbnotify('FAILED')
        bbnotify('SUCCESSFUL')
}

def bbnotify(status) {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: bb_creds,
    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        writeFile file: 'build.json', text: "{\"state\": \"${status}\", \"key\": \"${env.JOB_NAME}\", \"name\": \"${env.BUILD_TAG}\", \"url\": \"${env.BUILD_URL}\"}"
        
        sh '''#!/bin/bash -l
            echo $0
            curl -u $USERNAME:$PASSWORD -H "Content-Type: application/json" -X POST https://$bitbucket_url/rest/build-status/1.0/commits/$git_commit -d @build.json
            '''
    }
}
