FROM openjdk:17
VOLUME /tmp
ADD target/authentication-0.0.1-SNAPSHOT.jar auth.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar"]