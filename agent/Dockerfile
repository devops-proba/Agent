
#RUN mvn -B clean verify


FROM maven:3.6.3-ibmjava-8-alpine AS appProductBuild
ARG STAGE=dev
WORKDIR /usr/src/product
COPY . .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:8-jdk-alpine AS appProductRuntime
WORKDIR /app
COPY --from=appProductBuild /usr/src/product/target/products.jar ./
EXPOSE 8080
CMD java -jar products.jar