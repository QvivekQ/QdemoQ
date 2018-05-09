pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'JAVA 1.8'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -X clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                
            }
        }
    }
}
