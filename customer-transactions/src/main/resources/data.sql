DROP TABLE IF EXISTS Transaction;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Product;


CREATE TABLE Customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customerId INTEGER NOT NULL,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  age INTEGER NOT NULL,
  emailAddress VARCHAR(250) NOT NULL,
  location VARCHAR(50) NOT NULL
);

CREATE TABLE Product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  productCode VARCHAR(250) NOT NULL,
  cost DECIMAL(19,2) NOT NULL,
  status VARCHAR(25) NOT NULL
);

CREATE TABLE Transaction (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customerId INT NOT NULL,
  productId INT NOT NULL,
  quantity INTEGER NOT NULL,
  cost DECIMAL(19,2) NOT NULL,
  totalCost DECIMAL(19,2) NOT NULL,
  location VARCHAR(255) NOT NULL,
  status VARCHAR(25) NOT NULL,
  createDateTime TIMESTAMP NOT NULL,
  foreign key (customerId) references Customer(id),
  foreign key (productId) references Product(id)
);

INSERT INTO Customer (id, customerId, firstName, lastName, age, emailAddress, location) VALUES
  (1, 10001, 'Tony', 'Stark',45, 'tony.stark@gmail.com','Australia'),
  (2, 10002, 'Bruce', 'Banner',52, 'bruce.banner@gmail.com','US'),
  (3, 10003, 'Steve', 'Rogers',42, 'steve.rogers@hotmail.com','Australia'),
  (4, 10004, 'Wanda', 'Maximoff',38, 'wanda.maximoff@gmail.com','US'),
  (5, 10005, 'Natasha', 'Romanoff',40, 'natasha.romanoff@gmail.com','Canada');


INSERT INTO Product (id, productCode, cost, status) VALUES
  (1, 'PRODUCT_001', 50, 'Active'),
  (2, 'PRODUCT_002', 100, 'Inactive'),
  (3, 'PRODUCT_003', 200, 'Active'),
  (4, 'PRODUCT_004', 10, 'Inactive'),
  (5, 'PRODUCT_005', 500, 'Active');