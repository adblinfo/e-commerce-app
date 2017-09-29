# E-Commerce Application
** e-commerce sample application using powered by Spring, Angular 4, MongoDB, Redis and more._**

This sample e-commerce application demonstrates how to build an application using microservices architecture paradigm with Polyglot Languages (Java, JavaScript) & Polyglot Persistance software (MongoDB, Redis). This sample application includes following functional microservices & infrastructure microservices. All of these microservices are independently deployable applications and are organized around business capabilities.

###Functional Microservices
* **Product Catalog Microservice**
* **Cart Microservice**

The application is setup as multi-level gradle project where each microservice is arranged as a sub-module under single parent project. It enables to run each microservice individually.

##Prerequisites
* **_JDK 8_** - Install JDK 1.8 version from, http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* **_Gradle [Optional]_** - Download latest version of Gradle from https://gradle.org/gradle-download/ (binary distribution is good enough) and configure your machine with Gradle as specified at, https://docs.gradle.org/current/userguide/installation.html
* **_Node.js_** - Install Node.js from, https://nodejs.org/en/download/
* **_Angular 4_** - Install Angular 4 from, http://cli.angular.io

##Installation
#### Clone Repository
Clone respository source code by executing following instruction to any folder on your machine,
```
git clone https://github.com/damit619/e-commerce-app.git
cd e-commerce-app
```
###Building Application
#### Building Microservices
[Gradle](https://gradle.org/getting-started-gradle/) has been used as a build tool to build Spring Boot based Microservices applications. Issue following command on your terminal/console window,
```
gradlew clean build

cd product-catalog-service
gradlew bootRun

cd cart-service
gradlew bootRun
```
This command might take a while for first time as it needs to download serveral dependency libraries from Maven repository. This command will build & package all microservice applications.

#### Building Frontend App (Angular 4)
Frontend application is a Single Page Appliction built using Angular 4. The source code of this application is available under, '**_ecomm-webapp_**' folder. 
Issue following commands in sequence to build & run frontend app,
```
cd ecomm-webapp
npm install
ng serve
```
**Note**: 'npm install' command may take a while, as it needs to download all dependent npm modules used by frontend app.

Once above instructions successfully executed, you can view e-commerce application by browsing below URL,
[http://localhost:4200](http://localhost:4200)

##Prodcut Catalog Microservice
#### Overview
Product Catalog Microservice manages e-commerce application's products. This microservice is built as Spring Boot application with MongoDB as persistance store for product information.

#### REST API
Product Catalog REST API supports following opertations,

Method | URI | Description | Parameters | Request JSON | Response JSON
--- | --- | --- | --- | --- | ---
`GET` | *product-api/api/v1/products/recomendations* | List of recommended products | None |
`GET` | *product-api/api/v1/products/{id}* | Fetch product information based on id | None |
`PUT` | *product-api/api/v1/products* | Adds new product |
`POST` | *product-api/api/v1/products/{id}* | Updates existing product

##Cart Microservice
#### Overview
Cart Microservice provides e-commerce application's shopping cart functionality. This microservice is built as Spring Boot application with Redis as InMemory persistance store for cart information.

#### REST API
Cart REST API supports following opertations,

Method | URI | Description | Parameters | Request JSON | Response JSON
--- | --- | --- | --- | --- | ---
`GET` | *cart-api/api/v1/cart/{id}* | Fetches cart by id | None
`POST` | *cart-api/api/v1/cart/{id}* | Creates or updates cart

