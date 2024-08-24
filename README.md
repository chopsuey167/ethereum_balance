# Ethereum Balance

## Overview

The primary objective of this project is to retrieve the balance of an Ethereum wallet by using its address, via API
REST and gRPC service.

## Prerequisites

To clone and run this application, ensure you have the following installed on your computer:

- Git
- Java 17

## Getting started

Follow these steps to clone the repository, build the application, and run it:

```bash
# Clone this repository
$ git clone git@github.com:chopsuey167/ethereum_balance.git

# Go into the repository
$ cd ethereum_balance

# Build application
$ ./mvnw clean compile

# Run the app
$ ./mvnw spring-boot:run
```

### Testing the endpoints

REST API endpoint:
To test the REST API endpoint, you can use the following cURL command:

```bash
curl --location 'localhost:8080/api/v1/wallets/0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C/balance'
```

gRPC Service Request:
To test the gRPC service, use the following grpcurl command:

```bash
grpcurl -plaintext -d '{"address": "0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C" }' localhost:9090 EthereumService/GetWalletBalance
```

# Running test

To execute the application tests, run the following command:

```bash
./mvnw clean verify
```

## Running the application with Docker

To containerize application ensure is installed Docker.

You can build the application, create a Docker image, and run it using the following command (executed from within the
project directory):

```bash
sh build_run.sh
```

Refer to the  <a href="#Test-endpoints">Testing the Endpoints</a> section for instructions on how to test the endpoints.

## Deploying a Kubernetes Cluster Locally

To deploy the application on a local Kubernetes cluster, ensure the following tools are installed and configured:

- Docker (with Kubernetes enabled)
- Minikube
- kubectl CLI

Then, run the following command from within the project directory:

```bash
sh deploy_k8_cluster.sh
```

Refer to the  <a href="#Test-endpoints">Testing the Endpoints</a> section for instructions on how to test the endpoints.

## API Documentation

You can view the API documentation by visiting the following link after starting the
application:  [Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Possible improvements

- gRPC service documentation
- Implement spring security
- CI/CD pipeline