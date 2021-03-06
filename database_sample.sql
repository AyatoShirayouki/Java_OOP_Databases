CREATE DATABASE STOCKMARKET

USE STOCKMARKET

CREATE TABLE Cities(
	CITY_ID INT PRIMARY KEY IDENTITY NOT NULL,
	CITY_NAME NVARCHAR(20) NOT NULL
)

CREATE TABLE Users(
	USER_ID INT PRIMARY KEY IDENTITY NOT NULL,
	FIRST_NAME NVARCHAR(20) NOT NULL,
	LAST_NAME NVARCHAR(20) NOT NULL,
	AGE INT NOT NULL,
	CITY INT FOREIGN KEY REFERENCES Cities(CITY_ID),
)

CREATE TABLE Products(
	PRODUCT_ID INT PRIMARY KEY IDENTITY NOT NULL,
	QUANTITY INT NOT NULL,
	PRICE_ON_DELIVERY DECIMAL(19,2) NOT NULL,
	PRICE_ON_SALE DECIMAL(19,2) NOT NULL,
)

CREATE TABLE Sales(
	SALE_ID INT PRIMARY KEY IDENTITY NOT NULL,
	SOLD_PRODUCT_ID INT NOT NULL FOREIGN KEY REFERENCES Products(PRODUCT_ID),
	QUANTITY_SOLD INT NOT NULL,
	PROFIT DECIMAL(19,2) NOT NULL,
	SELLER_ID INT NOT NULL FOREIGN KEY REFERENCES Users(USER_ID),
	BUYER_ID INT NOT NULL FOREIGN KEY REFERENCES Users(USER_ID)
)