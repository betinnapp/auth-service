FROM openjdk:11-jre-slim
EXPOSE 8081
VOLUME /tmp
COPY . .
ENTRYPOINT ["java","-jar","target/auth-service-0.0.1-SNAPSHOT.jar"]