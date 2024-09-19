pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
    stages {
        stage('Build') {
            steps {

                git branch: 'main', url: 'https://github.com/RahulMasuram/OpenCartDemo1.git'
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    testNG reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'
                }
            }
        }
    }

}