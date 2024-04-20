pipeline {

    agent any

    tools { 
        maven 'my_maven' 
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
                    sh 'docker build -t irohas2004/ecommerce:2.4 .'
                    sh 'docker push irohas2004/ecommerce:2.4'
                }
            }
        }

       stage('Deploy APP to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker-compose -f docker-compose.yaml up -d'
                sh 'docker run -d --name ecommerce-app -v uploadsImage:/app/src/main/resources/static/uploads -p 8081:8081 --link mysql_db:mysql_db irohas2004/ecommerce:2.4'
            }
        }
 
    }
}
