pipeline {
    agent any
    tools {
        nodejs 'node-18.8'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    echo 'npm install process...'
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                     withCredentials([usernamePaswrod(credentialsId: 'DockerHub_credentials', passwordVariable: 'Pass', usernameVariable: 'USER')]) {
                        sh 'docker build -t ghazouanihm/jma-app:1.0 .'
                        sh "echo $PASS |docker login -u $USER --password-stdin"
                        sh 'docker push ghazouanihm/jma-app:1.0'
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