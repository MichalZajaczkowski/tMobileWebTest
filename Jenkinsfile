pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test on Chrome') {
            steps {
                script {
                    withEnv(['BROWSER=chrome']) {
                        sh 'mvn test -Dbrowser=chrome'
                    }
                }
            }
        }

        stage('Test on Firefox') {
            steps {
                script {
                    withEnv(['BROWSER=firefox']) {
                        sh 'mvn test -Dbrowser=firefox'
                    }
                }
            }
        }

        stage('Generate Reports') {
            steps {
                sh 'mvn allure:report'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/allure-maven-plugin',
                    reportFiles: 'index.html',
                    reportName: 'Allure Report'
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build Successful!'
        }
        failure {
            echo 'Build Failed!'
        }
        unstable {
            echo 'Build Unstable!'
        }
    }
}