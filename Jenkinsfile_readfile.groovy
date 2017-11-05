#!Groovy

// slave lease 1

node() {
    stage 'Obtain Tag'
        sh "git describe --tags > git-active-tag.txt"

    stage 'Load Env Vars'
        env.DOCKER_TAG = readFile('git-active-tag.txt').trim()

    stage 'Stash Files'
        stash includes: 'git-active-tag.txt', name: 'active-tag'
}

// slave lease 2

node() {
    stage 'Unstash Files'
        unstash 'active-tag'

    stage 'Load Env Vars'
        env.DOCKER_TAG = readFile('git-active-tag.txt').trim()
}
