pipeline {
    agent any
    environment {
        KUBECONFIG = "/Users/sagarkedar/.kube/config"
    }
    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-repo/jenkins-k8s-pipeline.git'
            }
        }

        stage('Apply Kubernetes Manifests') {
            steps {
                sh 'kubectl apply -f k8s-deployment-svc.yaml'
            }
        }

        stage('Verify Deployment') {
            steps {
                sh 'kubectl get pods'
                sh 'kubectl get svc myapp-service'
            }
        }
    }
}
