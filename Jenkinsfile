pipeline {
    agent any

    environment {
        DEPLOY_USER = 'ayoubroot'
        DEPLOY_HOST = '172.31.252.17'
        DEPLOY_PATH = 'spring-app'
        JAR_PATH = 'target/*.jar'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: '1c70b969-57ff-4c87-bb30-ae5a4e9e52f6',
                    url: 'https://github.com/ayoubMah/springBootDemo.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'DeploymentVM',
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "${JAR_PATH}",
                                    removePrefix: 'target/',
                                    remoteDirectory: "${DEPLOY_PATH}",
                                    //execCommand: 'sudo systemctl restart helloworld.service'
                                )
                            ],
                            usePty: false,
                            continueOnError: false,
                            failOnError: true
                        )
                    ]
                )
            }
        }
    }
}