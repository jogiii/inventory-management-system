pipeline {
    agent any

    tools {
        jdk 'Java17' // Ensure this tool is configured in Jenkins
    }

    environment {
        // defined in Jenkins credentials or environment
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // 'SonarQube' must be configured in Jenkins
                    sh './gradlew sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            junit 'build/test-results/test/*.xml'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
