DROP TABLE IF EXISTS Transaction;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Currency;

CREATE TABLE Currency (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  country VARCHAR(250) NOT NULL,
  description VARCHAR(250) NULL
);

CREATE TABLE User (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstName VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  phoneNumber VARCHAR(25) NOT NULL,
  emailAddress VARCHAR(250) NOT NULL
);


CREATE TABLE Account (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  accountNumber VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(25) NOT NULL,
  balanceDate DATE NOT NULL,
  currencyId INT NOT NULL,
  openingAvailableBalance DECIMAL(19,2) NOT NULL,
  userId INT NOT NULL,
  foreign key (currencyId) references Currency(id),
  foreign key (userId) references User(id)
);

CREATE TABLE Transaction (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  accountId INT NOT NULL,
  valueDate DATE NOT NULL,
  amount DECIMAL(19,2) NULL,
  type VARCHAR(25) NOT NULL,
  narrative VARCHAR(250) NOT NULL,
  foreign key (accountId) references Account(id)
);



INSERT INTO Currency (id, name, country, description) VALUES
  (1, 'SGD', 'Singapore', 'Singapore Dollar'),
  (2, 'AUD', 'Australia', 'Australian Dollar'),
  (3, 'USD', 'United States of America', 'American Dollar');

INSERT INTO User (id, firstName, lastName, phoneNumber, emailAddress) VALUES
  (1, 'Dinesh', 'Padmanabhan', '9876543210','abc@xyz.com'),
  (2, 'Mayank', 'Agrawal', '9999999999','xyz@abc.com'),
  (3, 'Ashish', 'Pathak', '8888888888','abc@efg.com');

INSERT INTO Account (id, accountNumber, name, type, balanceDate, currencyId, openingAvailableBalance, userId) VALUES
  (1, '585309209', 'SGSavings209', 'Savings', TO_DATE( '21-MAR-2021', 'DD-MON-YYYY'), 1, 84327.51, 1),
  (2, '321145205', 'SGSavings205', 'Savings', TO_DATE( '21-MAR-2021', 'DD-MON-YYYY'), 1, 12474.00, 1),
  (3, '195582698', 'SGSavings698', 'Savings', TO_DATE( '21-MAR-2021', 'DD-MON-YYYY'), 1, 3452.12, 1),
  (4, '656834394', 'SGSavings394', 'Savings', TO_DATE( '21-MAR-2021', 'DD-MON-YYYY'), 1, 34524.21, 1),
  (5, '791066619', 'AUSavings619', 'Savings', TO_DATE( '08-JAN-2021', 'DD-MON-YYYY'), 2, 4526.11, 2),
  (6, '947398538', 'AUSavings538', 'Savings', TO_DATE( '12-JAN-2021', 'DD-MON-YYYY'), 2, 42342.64, 2),
  (7, '486192526', 'AUSavings526', 'Savings', TO_DATE( '22-APR-2021', 'DD-MON-YYYY'), 2, 4523.19, 2),
  (8, '703572948', 'AUSavings948', 'Savings', TO_DATE( '29-JAN-2021', 'DD-MON-YYYY'), 2, 12445.00, 2),
  (9, '263935631', 'USSavings631', 'Savings', TO_DATE( '08-FEB-2021', 'DD-MON-YYYY'), 3, 928.00, 3),
  (10, '593063453', 'USSavings453', 'Savings', TO_DATE( '18-MAR-2021', 'DD-MON-YYYY'), 3, 3453.43, 3),
  (11, '104204575', 'USSavings933', 'Savings', TO_DATE( '23-JAN-2021', 'DD-MON-YYYY'), 3, 68462.05, 3),
  (12, '675225793', 'USSavings793', 'Savings', TO_DATE( '08-JAN-2021', 'DD-MON-YYYY'), 3, 8942.00, 3);

INSERT INTO Transaction (id, accountId, valueDate, amount, type, narrative) VALUES
  (1, 1, TO_DATE( '12-JAN-2021', 'DD-MON-YYYY'), 94.32, 'Debit', 'Groceries'),
  (2, 1, TO_DATE( '14-JAN-2021', 'DD-MON-YYYY'),  341, 'Credit', 'Transfer to Dinesh'),
  (3, 1, TO_DATE( '15-JAN-2021', 'DD-MON-YYYY'),  120, 'Credit', 'Internet Reimbursment'),
  (4, 1, TO_DATE( '18-JAN-2021', 'DD-MON-YYYY'), 1521,  'Debit', 'Rent deduction'),
  (5, 1, TO_DATE( '23-JAN-2021', 'DD-MON-YYYY'), 90.21,  'Debit', 'electricity bill'),
  (6, 2, TO_DATE( '05-MAR-2021', 'DD-MON-YYYY'), 134.21,  'Debit', 'Groceries'),
  (7, 2, TO_DATE( '09-MAR-2021', 'DD-MON-YYYY'), 1200, 'Credit', 'Transfer to Mayank'),
  (8, 2, TO_DATE( '12-MAR-2021', 'DD-MON-YYYY'), 120, 'Credit', 'Internet Reimbursment'),
  (9, 2, TO_DATE( '18-MAR-2021', 'DD-MON-YYYY'), 1800, 'Debit', 'Rent deduction'),
  (10, 2, TO_DATE( '27-MAR-2021', 'DD-MON-YYYY'), 110.11, 'Debit', 'electricity bill'),
  (11, 3, TO_DATE( '02-FEB-2021', 'DD-MON-YYYY'), 224.54, 'Debit', 'Groceries'),
  (12, 3, TO_DATE( '07-FEB-2021', 'DD-MON-YYYY'), 250, 'Credit', 'Transfer to Ashish'),
  (13, 3, TO_DATE( '14-FEB-2021', 'DD-MON-YYYY'), 80, 'Credit', 'Internet Reimbursment'),
  (14, 3, TO_DATE( '18-FEB-2021', 'DD-MON-YYYY'), 1200, 'Debit', 'Rent deduction'),
  (15, 3, TO_DATE( '23-FEB-2021', 'DD-MON-YYYY'), 76.37, 'Debit', 'electricity bill');

