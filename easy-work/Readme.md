# Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Gradle - 5.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/taiduc1988/test.git
```

**2. Create Mysql database**
```bash
create database works_app
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using Gradle**

```bash
gradlew bootWar
```

Alternatively, you can run the app without packaging it using -

```bash
java -jar build/libs/easy-work-0.0.1.war
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/works
    
    POST /api/works
    
    GET /api/works/{noteId}
    
    PUT /api/works/{noteId}
    
    DELETE /api/works/{noteId}

You can test them using postman or any other rest client.

## Learn more

You can find the tutorial for this application on my blog -

<https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/>
