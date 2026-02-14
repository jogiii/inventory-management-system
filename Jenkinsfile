pipeline {
    agent any
    
    

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Grant Permissions') {
            steps {
                sh 'chmod +x gradlew'
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
                withSonarQubeEnv('SonarQube') {
                    // 'SonarQube' matched the name you configured in Jenkins
                    // Force the URL to use the docker service name, overriding gradle.properties
                    sh './gradlew sonar -Dsonar.host.url=http://sonarqube:9000'
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
