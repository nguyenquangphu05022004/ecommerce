pipeline {

    agent any

    tools { 
        maven 'my_maven' 
        docker 'my_docker'
    }
    stages {

        stage('Build with Maven') {
            steps {
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
    }
}
