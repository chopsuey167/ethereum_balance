# Ethereum Balance

## About

The primary objective of this project is to retrieve the balance of an Ethereum wallet by using its address, via API
REST and gRPC service.

## How to use

To clone and run this application, you'll need Git and Java 17 installed on your computer. From your command line:

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

### Test endpoints

Rest endpoint cUrl:

```bash
curl --location 'localhost:8080/api/v1/wallets/0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C/balance'
```

gRpc Service Request:

```bash
grpcurl -plaintext -d '{"address": "0xC61b9BB3A7a0767E3179713f3A5c7a9aeDCE193C" }' localhost:9090 EthereumService/GetWalletBalance
```

# Running test

To execute run application tests, from your command line:

```bash
./mvnw clean verify
```

## Run application with Docker

To build application, create docker image and run. From your command line (inside project directory):

```bash
sh build_run.sh
```

How to test endpoints <a href="#Test-endpoints">here</a>

## Deploy k8s cluster locally

To perform this action you'll need the following tools installed in your computer:

- Kubernetes feature need to be active in docker dashboard
- Minikube
- kubectl cli

From your command line (inside project directory):

```bash
sh deploy_k8_cluster.sh
```

How to test endpoints <a href="#Test-endpoints">here</a>

## API Documentation

You can see api documentation in the following [link](http://localhost:8080/swagger-ui/index.html) (after start
application):
