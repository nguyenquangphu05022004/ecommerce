pipeline {

    agent any

    tools { 
        maven 'my_maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('MYSQL_ROOT_LOGIN')
    }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Packaging/Pushing imagae') {

            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t irohas2004/ecommerce .'
                    sh 'docker push irohas2004/ecommerce'
                }
            }
        }

       stage('Deploy APP to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker-compose --version'
                sh 'docker-compose -f docker-compose.yaml up -d'
            }
        }
 
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}