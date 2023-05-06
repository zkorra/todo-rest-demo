FROM adoptopenjdk/openjdk11

WORKDIR /app
COPY ./target/todo-rest-demo-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar", "todo-rest-demo-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080