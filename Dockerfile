FROM jenkins/inbound-agent:latest

USER root

# Install kubectl
RUN apt-get update && apt-get install -y curl && \
    curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    chmod +x kubectl && mv kubectl /usr/local/bin/ && \
    ln -s /usr/local/bin/kubectl /usr/bin/kubectl && \
    kubectl version --client

# Verify permissions and ownership
RUN chown jenkins:jenkins /usr/local/bin/kubectl /usr/bin/kubectl

USER jenkins