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
                    gv.bump_bundle_version_and_create_pr_to_ios_enterprise_app()
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
