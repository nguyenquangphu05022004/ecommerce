pipeline {

    agent any

    tools { 
        maven 'my-maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-db')
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

       stage('Deploy MySQL to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh "docker run --name mysql_db --rm --network dev -v my-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${mysql-db} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
                sh 'sleep 20'
                sh "docker exec -i mysql_db mysql --user=root --password=${mysql-db} < backup.sql"
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker container run -d --rm --name ecommerce-my-app -p 8081:8081 --network irohas2004/ecommerce'
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
