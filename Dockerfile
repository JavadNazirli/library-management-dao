FROM openjdk:17-jdk-slim
WORKDIR /app
COPY library-management-dao.jar /app/library-management-dao.jar
COPY lib/postgresql-42.7.5.jar /app/lib/postgresql-42.7.5.jar
COPY lib/opencsv-5.10.jar /app/lib/opencsv-5.10.jar
COPY lib/gson-2.12.1.jar /app/lib/gson-2.12.1.jar
COPY lib/commons-lang3-3.12.0.jar /app/lib/commons-lang3-3.12.0.jar
COPY lib/jackson-dataformat-xml-2.17.2.jar /app/lib/jackson-dataformat-xml-2.17.2.jar
COPY lib/jackson-core-2.18.3.jar /app/lib/jackson-core-2.18.3.jar
COPY lib/jackson-annotations-2.18.3.jar /app/lib/jackson-annotations-2.18.3.jar
COPY lib/jackson-databind-2.17.2.jar /app/lib/jackson-databind-2.17.2.jar
COPY lib/stax2-api-3.0.1.jar /app/lib/stax2-api-3.0.1.jar
COPY lib/woodstox-core-7.1.0.jar /app/lib/woodstox-core-7.1.0.jar
ENTRYPOINT ["java", "-cp", "library-management-dao.jar:lib/postgresql-42.7.5.jar:lib/opencsv-5.10.jar:lib/gson-2.12.1.jar:lib/commons-lang3-3.12.0.jar:lib/jackson-dataformat-xml-2.17.2.jar:lib/jackson-core-2.18.3.jar:lib/jackson-annotations-2.18.3.jar:lib/jackson-databind-2.17.2.jar:lib/stax2-api-3.0.1.jar:lib/woodstox-core-7.1.0.jar"]
CMD ["main.utils.Main"]