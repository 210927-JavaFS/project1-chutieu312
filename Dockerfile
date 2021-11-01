FROM maven:3-jdk-8
COPY target/*-dependencies.jar ERS.jar
EXPOSE 8081
CMD ["java","-jar","ERS.jar"]
