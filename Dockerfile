# Gradle with JDK 23
FROM gradle:8.10.2-jdk23
LABEL authors="otagay"

WORKDIR /app
COPY . .

# Ensure allure results always go under build/
ENV ALLURE_RESULTS_DIR=/app/build/allure-results
ENV GRADLE_OPTS="-Dallure.results.directory=/app/build/allure-results"

EXPOSE 8080

# Run tests, generate report, and serve via Gradle plugin
CMD ["bash", "-lc", "./gradlew clean runTestNG allureReport allureServe"]
