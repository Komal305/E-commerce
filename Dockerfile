FROM openjdk:17
ADD NorthWind-0.0.1-SNAPSHOT.jar northwind.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","northwind.jar"]