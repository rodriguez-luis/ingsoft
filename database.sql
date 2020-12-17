drop database store;
create database store;
use store;
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-12-17 18:19:41.61

-- tables
-- Table: cart
CREATE TABLE cart (
                      cart_id int NOT NULL AUTO_INCREMENT,
                      username varchar(50) NOT NULL,
                      cart_status int NOT NULL,
                      status int NOT NULL,
                      tx_id int NOT NULL,
                      tx_host varchar(50) NOT NULL,
                      tx_user_id int NOT NULL,
                      tx_date timestamp NOT NULL,
                      CONSTRAINT cart_pk PRIMARY KEY (cart_id)
);

-- Table: cart_details
CREATE TABLE cart_details (
                              details_id int NOT NULL AUTO_INCREMENT,
                              product_id int NOT NULL,
                              cart_id int NOT NULL,
                              quantity int NOT NULL,
                              price numeric(12,6) NOT NULL,
                              status int NOT NULL,
                              tx_id int NOT NULL,
                              tx_host varchar(50) NOT NULL,
                              tx_user_id int NOT NULL,
                              tx_date timestamp NOT NULL,
                              CONSTRAINT cart_details_pk PRIMARY KEY (details_id)
);

-- Table: orders
CREATE TABLE orders (
                        order_id int NOT NULL AUTO_INCREMENT,
                        date timestamp NOT NULL,
                        total numeric(12,6) NOT NULL,
                        address varchar(100) NOT NULL,
                        cart_id int NOT NULL,
                        username varchar(50) NOT NULL,
                        delivery_boy varchar(50) NOT NULL,
                        order_status int NOT NULL,
                        status int NOT NULL,
                        tx_id int NOT NULL,
                        tx_host varchar(50) NOT NULL,
                        tx_user_id int NOT NULL,
                        tx_date timestamp NOT NULL,
                        CONSTRAINT orders_pk PRIMARY KEY (order_id)
);

-- Table: product
CREATE TABLE product (
                         product_id int NOT NULL AUTO_INCREMENT,
                         product_name varchar(64) NOT NULL,
                         model varchar(50) NOT NULL,
                         product_description varchar(500) NOT NULL,
                         stock int NOT NULL,
                         weight double(6,3) NOT NULL,
                         unit_price numeric(12,6) NOT NULL,
                         currency int NOT NULL,
                         tx_id int NOT NULL DEFAULT 1,
                         tx_host varchar(50) NOT NULL DEFAULT '127.0.0.1',
                         tx_user_id int NOT NULL DEFAULT 0,
                         tx_date timestamp NOT NULL DEFAULT '2020-11-22 00:00:00',
                         status int NOT NULL DEFAULT 1,
                         brand varchar(50) NOT NULL DEFAULT 1,
                         img varchar(150) NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (product_id)
);

-- Table: transaction
CREATE TABLE transaction (
                             transaction_id int NOT NULL AUTO_INCREMENT,
                             tx_host varchar(50) NOT NULL DEFAULT '127.0.0.1',
                             tx_user_id int NOT NULL DEFAULT 0,
                             tx_date timestamp NOT NULL DEFAULT '2020-11-22 00:00:00',
                             CONSTRAINT transaction_pk PRIMARY KEY (transaction_id)
);

-- Table: user
CREATE TABLE user (
                      username varchar(50) NOT NULL,
                      password varchar(50) NOT NULL,
                      first_name varchar(50) NOT NULL,
                      last_name varchar(50) NOT NULL,
                      email varchar(50) NOT NULL,
                      privilege int NOT NULL,
                      status int NOT NULL,
                      tx_id int NOT NULL,
                      tx_host varchar(50) NOT NULL,
                      tx_user_id int NOT NULL,
                      tx_date timestamp NOT NULL,
                      CONSTRAINT user_pk PRIMARY KEY (username)
);

-- foreign keys
-- Reference: cart_details_cart (table: cart_details)
ALTER TABLE cart_details ADD CONSTRAINT cart_details_cart FOREIGN KEY cart_details_cart (cart_id)
    REFERENCES cart (cart_id);

-- Reference: cart_details_product (table: cart_details)
ALTER TABLE cart_details ADD CONSTRAINT cart_details_product FOREIGN KEY cart_details_product (product_id)
    REFERENCES product (product_id);

-- Reference: cart_user (table: cart)
ALTER TABLE cart ADD CONSTRAINT cart_user FOREIGN KEY cart_user (username)
    REFERENCES user (username);

-- Reference: checkout_cart (table: orders)
ALTER TABLE orders ADD CONSTRAINT checkout_cart FOREIGN KEY checkout_cart (cart_id)
    REFERENCES cart (cart_id);

-- Reference: order_user (table: orders)
ALTER TABLE orders ADD CONSTRAINT order_user FOREIGN KEY order_user (username)
    REFERENCES user (username);

-- Reference: order_user_2 (table: orders)
ALTER TABLE orders ADD CONSTRAINT order_user_2 FOREIGN KEY order_user_2 (delivery_boy)
    REFERENCES user (username);

-- End of file.

