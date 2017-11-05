#!Groovy

// enjoy the random values ;) -elk

node() {
    stage 'Ask For Input'
        def userInput = input(
            id: 'userInput', message: 'Let\'s promote?', parameters: [
            [$class: 'TextParameterDefinition', defaultValue: 'null', description: 'process-pid', name: 'pid']
        ])

    stage 'Show User Input'
        println userInput
}
