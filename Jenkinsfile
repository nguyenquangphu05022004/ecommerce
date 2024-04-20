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
                sh 'mvn clean install -Dmaven.test.failure.ignore=true'
            }
        }
        stage('Packaging/Pushing imagae') {

            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t irohas2004/ecommerce:2.6 .'
                    sh 'docker push irohas2004/ecommerce:2.6'
                }
            }
        }

       stage('Deploy APP to DEV') {
            steps {
                echo 'Deploying and cleaning'
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker rm -f mysql_db 1> /dev/null 2>&1'
                    sh 'docker rm -f ecommerce-app 1> /dev/null 2>&1'
                    sh 'docker rm -f phpmyadmin 1> /dev/null 2>&1'
                    sh 'docker network rm -f mynetwork'

                    sh 'docker network create mynetwork'
                    sh 'docker run -d --name mysql_db -v mydata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=irohas2004 --network mynetwork mysql:8.0'
                    sh 'docker run -d --name ecommerce-app -v uploadsImage:/app/src/main/resources/static/uploads -p 8081:8081 --network mynetwork irohas2004/ecommerce:2.6'
                    sh 'docker run -d --name phpmyadmin -p 8085:80 -e PMA_HOST=mysql_db --network mynetwork --link mysql_db phpmyadmin'
                }
            }
        }
 
    }
}
