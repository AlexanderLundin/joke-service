# joke-service

## Steps to build with docker
### Retrieve the image from docker hub with docker toolbox or other docker CLI

$ docker pull alundin/jokeservice

$ docker run -p 8080:8080 --rm alundin/jokeservice

$ docker-machine ip

Open up brower on the ip address found from command above + ":8080/api/jokes"
