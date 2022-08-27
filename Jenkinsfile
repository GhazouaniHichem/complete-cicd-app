def gv

pipeline {
    agent any
    tools {
        nodejs 'node-18.8'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    sh 'cd app'
                    sh 'npm install'
                    sh 'cd ..'
                    echo 'npm install process success...'
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                     withCredentials([usernamePassword(credentialsId: 'DockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t ghazouanihm/cicd-app:1.1 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push ghazouanihm/cicd-app:1.1'
                    }
                 
                    sh 'docker build -t ghazouanihm/cicd-app:1.1 .'
                    sh 'docker push ghazouanihm/cicd-app:1.1'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    echo "Deploying the application..."
                }
            }
        }
    }
}