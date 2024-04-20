pipeline {

    agent any

    tools { 
        maven 'my_maven' 
    }
    stages {
        stage('Build with Maven') {
            steps {
                script {
                    def dockerHome = tool 'my_docker'
                  env.PATH = "${dockerHome}/bin:${env.PATH}"
                }
                sh 'mvn --version' 
                sh 'java -version'
            }
        }
        stage('Packaging/Pushing imagae') {

            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t irohas2004/ecommerce:2.0 .'
                    sh 'docker push irohas2004/ecommerce:2.0'
                }
            }
        }

       stage('Deploy APP to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker-compose -f docker-compose.yaml up -d'
            }
        }
 
    }
}
