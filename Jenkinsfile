pipeline {
    agent any

    tools {
        // Define the tools you want to use
        maven 'Maven 3.6.3'
        jdk 'JDK 17'
    }

    environment {
        // Define any environment variables you need
        API_GATEWAY_PATH = 'api-gateway'
        // CATALOG_SERVICE_PATH = 'catalog-service'
        IDENTITY_SERVICE_PATH = 'identity-service'
        PROFILE_SERVICE_PATH = 'profile-service'
        SERVICE_REGISTRY_PATH = 'service-registry'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                // Add your checkout commands here
                sh '''
                    echo 'Checking out the code from the repository...'
                '''
                checkout scm
                sh "git fetch --all"
            }
        }

        stage('Detect Changes') {
            script {
                // Init changed flags
                env.API_GATEWAY_CHANGED = 'false'
                // env.CATALOG_SERVICE_CHANGED = 'false'
                env.IDENTITY_SERVICE_CHANGED = 'false'
                env.PROFILE_SERVICE_CHANGED = 'false'
                env.SERVICE_REGISTRY_CHANGED = 'false'
                
                // If first build, set the environment variable to true
                if (params.FORCE_BUILD_ALL = true || currentBuild.previousBuild == null) {
                    env.API_GATEWAY_CHANGED = 'true'
                    // env.CATALOG_SERVICE_CHANGED = 'true'
                    env.IDENTITY_SERVICE_CHANGED = 'true'
                    env.PROFILE_SERVICE_CHANGED = 'true'
                    env.SERVICE_REGISTRY_CHANGED = 'true'
                } else {
                    try {
                        def currentCommit = sh(scipt: "giv rev-parse HEAD", returnStdout: true).trim()

                        def previousCommit = ""
                        if (currentBuild.previousSuccessBuild == null) {
                            previousCommit = currentBuild.previousSuccessfulBuild.getEnvironment().get("GIT_COMMIT")
                            if (!previousCommit) {
                                previousCommit = sh(script: "git rev-parse HEAD~1", returnStdout: true).trim()
                            }                        } else {
                            previousCommit = currentBuild.previousBuild.getEnvironment().get("GIT_COMMIT")
                        }

                        def changeSet = sh(script: "git diff --name-only ${previousCommit} ${currentCommit}", returnStdout: true).trim()

                        if (changeSet) {
                            changeSet = changeSet.split('\n')

                            if (changeSet.any { it.startsWith("${env.API_GATEWAY_PATH}/") }) {
                                env.API_GATEWAY_CHANGED = 'true'
                            }
                            // if (changeSet.any { it.startsWith("${env.CATALOG_SERVICE_PATH}/") }) {
                            //     env.CATALOG_SERVICE_CHANGED = 'true'
                            // }
                            if (changeSet.any { it.startsWith("${env.IDENTITY_SERVICE_PATH}/") }) {
                                env.IDENTITY_SERVICE_CHANGED = 'true'
                            }
                            if (changeSet.any { it.startsWith("${env.PROFILE_SERVICE_PATH}/") }) {
                                env.PROFILE_SERVICE_CHANGED = 'true'
                            }
                            if (changeSet.any { it.startsWith("${env.SERVICE_REGISTRY_PATH}/") }) {
                                env.SERVICE_REGISTRY_CHANGED = 'true'
                            }
                        } else {
                            echo "No changes detected in the api-gateway directory."
                            currentBuild.result = 'ABORTED'
                            error("No changes detected in the api-gateway directory.")
                        }
                    } catch {
                        echo "Error while checking for changes: ${it}"
                        currentBuild.result = 'FAILURE'
                        error("Error while checking for changes.")
                    }

                    // Set the environment variable to true if there are changes in the api-gateway directory
                } 
            }
        }
     
        stage('Build') {
            parallel {
                stage('Build API Gateway') {
                    when {
                        expression { env.API_GATEWAY_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Building API Gateway...'
                        // Add your build commands here
                        sh '''
                            echo 'Building the API Gateway...'
                        '''
                        dir "${env.API_GATEWAY_PATH}" {
                            sh '''
                                echo 'Building the API Gateway...'
                                mvn clean package
                            '''
                        }
                    }
                }
                // stage('Build Catalog Service') {
                //     when {
                //         expression { env.CATALOG_SERVICE_CHANGED == 'true' }
                //     }
                //     steps {
                //         echo 'Building Catalog Service...'
                //         // Add your build commands here
                //         sh '''
                //             echo 'Building the Catalog Service...'
                //         '''
                //         dir "${env.CATALOG_SERVICE_PATH}" {
                //             sh '''
                //                 echo 'Building the Catalog Service...'
                //                 mvn clean package
                //             '''
                //         }
                //     }
                // }
                stage('Build Identity Service') {
                    when {
                        expression { env.IDENTITY_SERVICE_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Building Identity Service...'
                        // Add your build commands here
                        sh '''
                            echo 'Building the Identity Service...'
                        '''
                        dir "${env.IDENTITY_SERVICE_PATH}" {
                            sh '''
                                echo 'Building the Identity Service...'
                                mvn clean package
                            '''
                        }
                    }
                }
                stage('Build Profile Service') {
                    when {
                        expression { env.PROFILE_SERVICE_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Building Profile Service...'
                        // Add your build commands here
                        sh '''
                            echo 'Building the Profile Service...'
                        '''
                        dir "${env.PROFILE_SERVICE_PATH}" {
                            sh '''
                                echo 'Building the Profile Service...'
                                mvn clean package
                            '''
                        }
                    }
                }
                stage('Build Service Registry') {
                    when {
                        expression { env.SERVICE_REGISTRY_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Building Service Registry...'
                        // Add your build commands here
                        sh '''
                            echo 'Building the Service Registry...'
                        '''
                        dir "${env.SERVICE_REGISTRY_PATH}" {
                            sh '''
                                echo 'Building the Service Registry...'
                                mvn clean package
                            '''
                        }
                    }
                } 
            }
        }
        stage('Test') {
            parallel {
                stage('Test API Gateway') {
                    when {
                        expression { env.API_GATEWAY_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Testing API Gateway...'
                        // Add your test commands here
                        sh '''
                            echo 'Testing the API Gateway...'
                        ''' 
                        dir "${env.API_GATEWAY_PATH}" {
                            sh '''
                                echo 'Running tests for the API Gateway...'
                                mvn verify
                            '''
                        }
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,  testResults:  "${env.API_GATEWAY_PATH}/target/surefire-reports/*.xml"
                        }
                    }
                }
                // stage('Test Catalog Service') {
                //     when {
                //         expression { env.CATALOG_SERVICE_CHANGED == 'true' }
                //     }
                //     steps {
                //         echo 'Testing Catalog Service...'
                //         // Add your test commands here
                //         sh '''
                //             echo 'Testing the Catalog Service...'
                //         '''
                //         dir "${env.CATALOG_SERVICE_PATH}" {
                //             sh '''
                //                 echo 'Running tests for the Catalog Service...'
                //                 mvn verify
                //             '''
                //         }
                //     }
                //     post {
                //         always {
                //             junit allowEmptyResults: true,  testResults:  "${env.CATALOG_SERVICE_PATH}/target/surefire-reports/*.xml"
                //         }
                //     }
                // }
                stage('Test Identity Service') {
                    when {
                        expression { env.IDENTITY_SERVICE_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Testing Identity Service...'
                        // Add your test commands here
                        sh '''
                            echo 'Testing the Identity Service...'
                        '''
                        dir "${env.IDENTITY_SERVICE_PATH}" {
                            sh '''
                                echo 'Running tests for the Identity Service...'
                                mvn verify
                            '''
                        }
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,  testResults:  "${env.IDENTITY_SERVICE_PATH}/target/surefire-reports/*.xml"
                        }
                    }
                }
                stage('Test Profile Service') {
                    when {
                        expression { env.PROFILE_SERVICE_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Testing Profile Service...'
                        // Add your test commands here
                        sh '''
                            echo 'Testing the Profile Service...'
                        '''
                        dir "${env.PROFILE_SERVICE_PATH}" {
                            sh '''
                                echo 'Running tests for the Profile Service...'
                                mvn verify
                            '''
                        }
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,  testResults:  "${env.PROFILE_SERVICE_PATH}/target/surefire-reports/*.xml"
                        }
                    }
                }
                stage('Test Service Registry') {
                    when {
                        expression { env.SERVICE_REGISTRY_CHANGED == 'true' }
                    }
                    steps {
                        echo 'Testing Service Registry...'
                        // Add your test commands here
                        sh '''
                            echo 'Testing the Service Registry...'
                        '''
                        dir "${env.SERVICE_REGISTRY_PATH}" {
                            sh '''
                                echo 'Running tests for the Service Registry...'
                                mvn verify
                            '''
                        }
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,  testResults:  "${env.SERVICE_REGISTRY_PATH}/target/surefire-reports/*.xml"
                        }
                    }
                } 
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying automation Deploy-staging...'
                // Add your deploy commands here
                sh '''
                    echo 'Deploying the project for testing webhook...'
                '''
            }
        }                                           
    }   

    post {
        always {
            echo 'Cleaning up...'
            // Add your cleanup commands here
            sh '''
                echo 'Cleaning up the workspace...'
            '''
            cleanWs()
        }
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}