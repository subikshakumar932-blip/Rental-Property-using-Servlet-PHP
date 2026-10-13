WEB-BASED RENTAL PROPERTY LISTING AND TENANT MANAGEMENT SYSTEM
===============================================================
Mini Project - JSP + Servlet + JDBC + MySQL

PROJECT: RentalPropertyMini

1. SOFTWARE NEEDED
------------------
- NetBeans 8.2 or newer
- JDK 8 (recommended for the lab environment)
- Apache Tomcat 9.x OR GlassFish 4.1.1
- MySQL 8.x
- Internet access once, so Maven can download dependencies

2. DATABASE SETUP
-----------------
1. Start MySQL service.
2. Open MySQL Command Line Client or CMD.
3. Run:
   SOURCE database/rental_db.sql;
   OR copy/paste the complete SQL file into MySQL.
4. The script creates database rental_db, tables and demo records.

If your MySQL root account has a password, edit:
src/main/java/com/rentalproperty/util/DBConnection.java

Change:
USER="root";
PASSWORD="";

Example:
USER="root";
PASSWORD="your_mysql_password";

3. DEMO LOGIN
-------------
OWNER:
Email    : owner@gmail.com
Password : 1234
Role     : Owner

TENANT:
Email    : tenant@gmail.com
Password : 1234
Role     : Tenant

4. OPEN IN NETBEANS
-------------------
A) Extract this ZIP.
B) NetBeans -> File -> Open Project.
C) Select the RentalPropertyMini folder.
D) NetBeans detects it as a Maven Web Application.
E) Set your Tomcat/GlassFish server in Project Properties -> Run.
F) Right-click project -> Run.

The application opens at:
http://localhost:8080/RentalPropertyMini/

5. FIRST RUN FLOW
------------------
Home -> Register/Login -> Owner Dashboard -> Add Property
Home -> Properties -> View Details -> Request Rent
Owner -> Rental Requests -> Update Pending/Approved/Rejected

6. IF MYSQL DRIVER IS NOT DOWNLOADED
-------------------------------------
The pom.xml contains MySQL Connector/J 8.4.0.
If NetBeans cannot download Maven dependencies, use your existing
mysql-connector-j-8.4.0.jar and add it to the project Libraries.
The JDBC code uses Class.forName("com.mysql.cj.jdbc.Driver").

7. IMPORTANT
------------
Do not use MySQL Workbench if your lab requires command line.
MySQL Command Line Client is enough.

This project is intentionally simple and suitable for an Internet
Programming Laboratory mini project. Passwords are stored as plain text
only for classroom demonstration; a real application should hash them.
