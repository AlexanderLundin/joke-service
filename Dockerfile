# Docker file for two phase build
# Phase 1 - Build the application in it's own container named "build"
FROM openjdk:8-jdk-alpine as build
VOLUME /tmp
COPY . .
RUN ./gradlew clean build
# Phase 2 - Build the actual docker container with only the jar file
FROM openjdk:8-jdk-alpine
WORKDIR /app
# Copy file from the "build container identified in line 3
COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
# Build docker image
# $ docker build -t jokeservice .
#
# Deploy joke service locally
# $ docker run -p 8080:8080 --rm jokeservice
#
# should be available at http://localhost:8080/api/jokes
# or http://192.168.99.100:8080/api/jokes
# or whever your docker is set to, use command below to see your docker ip
# $ docker-machine ip
#
# Push to dockerhub
# $ docker login
# $ docker tag jokeservice alundin/jokeservice
# $ docker push alundin/jokeservice
#
# Retrieve the image from docker hub
# $ docker pull dockerhandle/jokeservice
# $ docker run -p 8080:8080 --rm alundin/jokeservice