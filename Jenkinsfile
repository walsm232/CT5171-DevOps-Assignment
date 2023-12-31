pipeline {
    agent any

    environment {
        AWS_ACCOUNT_ID = credentials("aws-account-id")
        AWS_ACCESS_KEY_ID = credentials("aws-access-key")
        AWS_SECRET_ACCESS_KEY = credentials("aws-secret-access-key")
        AWS_DEFAULT_REGION = "eu-west-1"
        IMAGE_REPOSITORY_NAME = "ct5171-devops-assignment"
        IMAGE_TAG = "latest"
    }

    stages {
        stage ("Build") {
            steps {
                timeout(time: 10, unit: "MINUTES") {
                    sh "mvn clean"
                }
            }
        }
        stage ("Package") {
            steps {
                timeout(time: 10, unit: "MINUTES") {
                    sh "mvn package"
                }
            }
        }
        stage ("Login to AWS ECR") {
            steps {
                timeout(time: 1, unit: "MINUTES") {
                    sh '''
                        aws ecr get-login-password \
                            --region ${AWS_DEFAULT_REGION} | docker login \
                            --username AWS \
                            --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com
                    '''
                }
            }
        }
        stage ("Build Docker Image") {
            steps {
                timeout(time: 5, unit: "MINUTES") {
                    sh '''
                        DOCKER_BUILDKIT=1 docker build . \
                            -t ${IMAGE_REPOSITORY_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }
        stage ("Push to AWS ECR") {
            steps {
                timeout(time: 2, unit: "MINUTES") {
                    sh '''    
                        docker tag ${IMAGE_REPOSITORY_NAME}:${IMAGE_TAG} ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPOSITORY_NAME}:${IMAGE_TAG}
                        docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPOSITORY_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }
        stage("Deployment Confirmation") {
            steps {
                timeout(time: 15, unit: "MINUTES") {
                    script {
                        env.DEPLOY = input(
                                id: "deployConfirm", message: "Do you want to trigger a deployment?", parameters: [
                                [$class: "BooleanParameterDefinition", defaultValue: false, name: "Deploy"]
                        ])
                    }
                }
            }
        }
        stage ("Deploy Container") {
            when {
                expression {
                    env.DEPLOY.toBoolean()
                }
            }
            steps {
                timeout(time: 5, unit: "MINUTES") {
                    sh '''
                        docker ps -aq | xargs docker stop | xargs docker rm
                        docker run -d -p 9090:8080 --detach ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPOSITORY_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts allowEmptyArchive: true,
            artifacts: "target/*.war", fingerprint: true
        }
    }
}