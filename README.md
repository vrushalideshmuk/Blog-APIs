# Blog Application

## Overview

The Blog Application is a web application built using Java and Spring Boot. It allows users to create, read, update, and delete blog posts. Users can also comment on posts and manage their profiles.

## Features

- User authentication and authorization
- Role specific API access
- Create, read, update, and delete blog posts
- Comment on posts
- User profile management
- Responsive design
  

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven
- Swagger
- AWS

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven 3.6.0 or later
- MySQL 5.7 or later

## Getting Started
Update the application.properties file located in src/main/resources with your MySQL database configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
