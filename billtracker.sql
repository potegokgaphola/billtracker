DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS bills;

CREATE TABLE users (
	user_id INT PRIMARY KEY IDENTITY,
	username VARCHAR(100) NOT NULL UNIQUE,
	firstname VARCHAR(100) NOT NULL,
	lastname VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL
);

CREATE TABLE bills(
	bill_id INT PRIMARY KEY IDENTITY,
	bill_date DATE NOT NULL,
	bill_type VARCHAR(50) NOT NULL,
	bill_amount FLOAT NOT NULL,
	user_id INT FOREIGN KEY REFERENCES users(user_id),
	UNIQUE(bill_type, bill_date)
);