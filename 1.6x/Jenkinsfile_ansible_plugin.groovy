#!Groovy

scm_repo = ''
scm_creds = ''
scm_branch = ''

ansible_creds = ''

node() {
    stage 'Clean Workspace'
        sh 'rm -rf $PWD/*'
        
    stage 'Checkout'
        git branch: scm_branch, credentialsId: scm_creds, poll: false, url: scm_repo
        
    stage 'Ansible Function'
        ansiColor('xterm') {
            ansiblePlaybook colorized: true, credentialsId: ansible_creds, forks: 10, inventory: 'inventory.ini', limit: '', playbook: 'ls.yml', sudoUser: null
        }
}
