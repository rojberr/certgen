pipeline {
    agent {
        docker {
            image 'gradle:7.5.1-jdk17-alpine'
            args '-v /var/run/docker.sock:/var/run/docker.sock --memory 2g --cpus=1'
        }
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble'
            }
        }
        stage('Spotbugs static analysis') {
            options {
                timeout(time: 20, unit: 'MINUTES')
            }
            steps {
                sh './gradlew spotbugsMain spotbugsTest'
            }
        }
        stage('Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew test'
            }
        }
    }
}