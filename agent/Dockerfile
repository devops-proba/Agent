
#RUN mvn package -P${STAGE} -DskipTests
#RUN mvn -B clean verify  
#run: mvn -B clean verify -Dspring.profiles.active=test

FROM nikolina97/maven-repo:0.0.1 AS appProductBuild
ARG STAGE=dev
WORKDIR /usr/src/product
COPY . .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:8-jdk-alpine AS appProductRuntime
WORKDIR /app
COPY --from=appProductBuild /usr/src/product/target/products.jar ./
EXPOSE 8080
CMD java -jar products.jar