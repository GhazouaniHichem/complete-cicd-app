def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'DockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t ghazouanihm/cicd-app:1.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push ghazouanihm/cicd-app:1.1'
        }
}

def deployApp() {
    echo 'deploying the application...'
    sh 'ssh -i ~/web-server-key-pair.pem -o "StrictHostKeyChecking no" ec2-user@13.38.63.231 -y "git clone https://github.com/GhazouaniHichem/complete-cicd-app.git temp && mv -f temp/.git complete-cicd-app/.git && rm -rf temp && cd complete-cicd-app && docker-compose up -d"'
//    sh 'git clone https://github.com/GhazouaniHichem/complete-cicd-app.git'
//    sh 'cd complete-cicd-app'
//    sh 'docker-compose up -d'

}


return this
