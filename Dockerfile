# Base image
FROM openjdk:21-jdk-slim


# Set working directory
WORKDIR /library-management-dao

# Copy dependency libraries
COPY lib/ /library-management-dao/lib/

# Copy application JAR
COPY lib/library-management-dao.jar /library-management-dao/

# Command to run the application
CMD ["java", "-jar", "/library-management-dao/library-management-dao.jar"]