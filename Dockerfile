FROM openjdk:17
EXPOSE 8080
ADD target/aws-reader-docker.jar aws-reader-docker.jar
ENTRYPOINT ["java","-jar","/aws-reader-docker.jar"]