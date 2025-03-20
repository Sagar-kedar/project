# Project
This repo is for docker pipeline using jekins configuration as a code

## Start the minikube
 `minikube start` 

## Verify Minikube is running:
 `kubectl get nodes`

## Add jenkins repo 
 `helm repo add jenkins https://charts.jenkins.io` \
 `helm repo update` 

## Install Jenkins in a new namespace:
 `kubectl create namespace project` \
 `helm install jenkins jenkins/jenkins --namespace project`

This will install Jenkins with:
* A Jenkins deployment
* A Service (to expose Jenkins UI)

## Expose Jenkins UI
`kubectl port-forward --namespace project svc/jenkins 8080:8080` 

Now open http://localhost:8080 in your browser and log in with:
* Username: admin 
* Password: you will get password from Lens in the secrets 

✅ Jenkins is now running inside Minikube!

# Now that Jenkins is installed, we need to set it up for Kubernetes.
## Install Required Jenkins Plugins
Go to Jenkins → Manage Jenkins → Plugins and install:
* Kubernetes Plugin
* Pipeline Plugin
* Job DSL Plugin
* Docker Pipeline Plugin
* Git Plugin

## Add Kubernetes Credentials in Jenkins
1. Go to: Manage Jenkins → Manage Credentials → Global Credentials
2. Add a new "Kubernetes Configuration File"
3. Paste your Minikube kubeconfig: \
`cat ~./kube/config`
Copy the output and paste it into Jenkins.
4. Give it an ID → Set ID: kubeconfig
5. Save it.
