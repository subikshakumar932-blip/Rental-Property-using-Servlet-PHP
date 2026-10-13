CREATE DATABASE IF NOT EXISTS rental_db;
USE rental_db;

DROP TABLE IF EXISTS rental_requests;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  role ENUM('Owner','Tenant') NOT NULL DEFAULT 'Tenant'
);

CREATE TABLE properties (
  property_id INT PRIMARY KEY AUTO_INCREMENT,
  owner_id INT NOT NULL,
  title VARCHAR(150) NOT NULL,
  location VARCHAR(120) NOT NULL,
  type VARCHAR(50) NOT NULL,
  rent DECIMAL(10,2) NOT NULL,
  bedrooms INT NOT NULL,
  description TEXT,
  status ENUM('Available','Rented') NOT NULL DEFAULT 'Available',
  image VARCHAR(255) DEFAULT 'house1.svg',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE rental_requests (
  request_id INT PRIMARY KEY AUTO_INCREMENT,
  property_id INT NOT NULL,
  tenant_id INT NOT NULL,
  tenant_name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  message TEXT,
  request_date DATE NOT NULL,
  status ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
  FOREIGN KEY (tenant_id) REFERENCES users(user_id) ON DELETE CASCADE
);

INSERT INTO users(name,email,password,phone,role) VALUES
('Demo Owner','owner@gmail.com','1234','9876543210','Owner'),
('Demo Tenant','tenant@gmail.com','1234','9876501234','Tenant');

INSERT INTO properties(owner_id,title,location,type,rent,bedrooms,description,status,image) VALUES
(1,'2 BHK Apartment','Madurai','Apartment',12000,2,'Spacious 2 BHK apartment with good ventilation, parking and nearby facilities.','Available','house1.svg'),
(1,'3 BHK Villa','Virudhunagar','Villa',25000,3,'Independent villa with three bedrooms, parking and a peaceful residential area.','Available','house2.svg'),
(1,'1 BHK Independent House','Sivakasi','House',8000,1,'Affordable independent house suitable for a small family or working professional.','Available','house3.svg');
