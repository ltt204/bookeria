pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                // Add your build commands here
                sh '''
                    echo 'Building the project...'
                '''
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Add your test commands here
                sh '''
                    echo 'Running tests...'
                '''
            }
        }
        stage('Deliver') {
            steps {
                echo 'Deploying automation Deploy-staging...'
                // Add your deploy commands here
                sh '''
                    echo 'Delivering the project...'
                '''
            }
        }                                           
    }   
}