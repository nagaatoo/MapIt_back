./gradlew build -x test
docker build -t mapit .
docker run -p 8552:8552 mapit