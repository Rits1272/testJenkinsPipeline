def gv

pipeline {
    agent any

    stages {
        
        stage("build") {
            steps {
                script {
                    gv = load "shared_functions.groovy"
                }
            }
        }

        stage("test") {
            steps {
                script {
                    gv.something()
                }
                echo 'testing the application'
            }
        }

        stage("deploy") {
            steps {
                echo 'deploying the application'
            }
        }
    }
}
