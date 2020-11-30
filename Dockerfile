# Use Open JDK as base image
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /

# Take the jar from the build folder and add it to project as reviews.jar
ADD build/libs/accessories-gradle-0.0.1-SNAPSHOT.jar accessories-gradle.jar

# Expose PORT 8080
EXPOSE 8080

# Invoke java executable and run the reviews.jar file
# CMD java -jar reviews.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "accessories-gradle.jar"]