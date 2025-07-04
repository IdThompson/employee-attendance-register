FROM openjdk:17-jdk-slim

# Install netcat
RUN apt-get update && apt-get install -y netcat

# Copy the compiled JAR and wait script
COPY target/employee-attendance-register-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh

# Make the wait script executable
RUN chmod +x wait-for-mysql.sh

# Run the wait script then start the app
ENTRYPOINT ["./wait-for-mysql.sh", "mysql", "3306", "--", "java", "-jar", "app.jar"]
