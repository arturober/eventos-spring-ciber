./mvnw package -Dmaven.test.skip
sudo docker build -t arturober/ciberspring .
sudo docker push arturober/ciberspring:latest
