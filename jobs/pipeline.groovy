pipelineJob('MyPipelineJob') {
    description('This is a pipeline job created with Job DSL')   
    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    environment {
                        KUBECONFIG = "/Users/sagarkedar/.kube/config"
                    }
                    stages {
                        stage('Checkout Code') {
                            steps {
                                script {
                                    git branch: 'main', url: 'https://github.com/Sagar-kedar/project.git'
                                }
                            }
                        }

                        stage('Apply Kubernetes Manifests') {
                            steps {
                               script {
                                   sh 'brew install kubectl'
                                   sh 'kubectl apply -f k8s-deployment-svc.yaml'
                               }
                            }
                        }

                        stage('Verify Deployment') {
                            steps {
                               script {
                                   sh 'kubectl get pods'
                                   sh 'kubectl get svc myapp-service'
                               }
                            }
                        }
                    }
                }
            """)
        }
    }
}                
