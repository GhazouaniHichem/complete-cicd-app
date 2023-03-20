def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'DockerHub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'sudo docker build -t ghazouanihm/cicd-app:1.1 .'
        sh "echo $PASS | sudo docker login -u $USER --password-stdin"
        sh 'sudo docker push ghazouanihm/cicd-app:1.1'
        }
}

def deployApp() {
    echo 'deploying the application....'
    sh 'ssh -i ~/jenk_master_key.pem -o "StrictHostKeyChecking no" ec2-user@52.47.177.29 -y "git clone https://github.com/GhazouaniHichem/complete-cicd-app.git && cd complete-cicd-app && sudo docker-compose up -d"'
//    sh 'git clone https://github.com/GhazouaniHichem/complete-cicd-app.git'
//    sh 'cd complete-cicd-app'
//    sh 'docker-compose up -d'

}


return this
