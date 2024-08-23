#! /bin/bash
# Start minikube
minikube start

# Set docker env
eval $(minikube docker-env)

# Build image
docker build -t ethereum_balance:latest .

# k8s deployment
kubectl apply -f devops/kubernetes/deployment.yaml

# k8s service
kubectl apply -f devops/kubernetes/service.yaml

# Minikube tunnel
minikube tunnel

# kubectl delete service --all --namespace=default
# kubectl delete deployment --all --namespace=default