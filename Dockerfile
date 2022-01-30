FROM adoptopenjdk/openjdk17
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","--enable-preview","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
