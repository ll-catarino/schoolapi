FROM openjdk:11
COPY target/schoolAPI.jar schoolAPI.jar
CMD ["sleep", "5"] #wait for db to accept connections
CMD ["java", "-jar", "schoolAPI.jar"]
