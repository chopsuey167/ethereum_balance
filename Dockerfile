# Create image application
FROM openjdk:17-jdk-slim-buster
LABEL maintainer="ricardog.astocondor@gmail.com"
COPY target/ethereum_balance-0.0.1-SNAPSHOT.jar ethereum_balance.jar
ENTRYPOINT ["java","-jar","/ethereum_balance.jar"]
EXPOSE 8080 9090