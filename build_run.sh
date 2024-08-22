#! /bin/bash
# Build application
./mvnw clean package -DskipTests
# Create docker image
docker build -t ethereum_balance:latest .
# Run docker image
docker run -d -p 8080:8080 -p 9090:9090 ethereum_balance:latest