# Library ( Option №16)

User can place an order for book which is available now.
Admin can confirm this order and give book to user or decline order.
Also admin can add, edit, delete books from library, and see all readers with books they are reading.


# Requirments

JDK 1.8  
Apache Maven
MySQL

## Usage

Clone project to local repository  
Run SQL scripts(create.sql) from resources folder to create database.
Go to aplication.properties and change next fields
 - spring.datasource.username
 - spring.datasource.password
Run SQL scripts(insert.sql) from resources folder to fill database.
From root folder run - mvn spring-boot:run  
Use http://localhost:8080 to open the app

## Use below emails and password to login as user and admin
admin1@gmail.com  Adminadmin!  
user1@gmail.com   Useruser!

```

