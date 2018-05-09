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
                sh 'chmod 777 *'
                sh 'mvn -f pom.xml clean install'
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
