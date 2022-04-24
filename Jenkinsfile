pipeline {
    agent any

    stages {
        
        stage("build") {
            steps {
                script {
                    shared_functions.bump_bundle_version_and_create_pr_to_ios_enterprise_app
                }
            }
        }

        stage("test") {
            steps {
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
