#!groovy

pipeline {
  agent none
  stages {
     stage('Initialize'){
        def dockerHome = tool 'my_docker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
    }
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.5.0'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Docker Build') {
      agent any
      steps {
        sh 'docker build -t irohas2004/ecommerce:latest .'
      }
    }
  }
}