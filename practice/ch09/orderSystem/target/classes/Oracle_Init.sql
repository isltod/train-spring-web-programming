DROP TABLE order_item;
DROP TABLE orders;
DROP TABLE inventory;
DROP TABLE product;
DROP TABLE customer;

CREATE TABLE customer (
                          customer_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
                          customer_name VARCHAR(20) NOT NULL,
                          customer_address VARCHAR(60),
                          customer_email VARCHAR(40)
);
CREATE TABLE product (
                         product_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
                         product_name VARCHAR(40) NOT NULL,
                         product_description VARCHAR(200),
                         product_price INTEGER NOT NULL CHECK(product_price > 0)
);
CREATE TABLE inventory (
                           product_id INTEGER PRIMARY KEY,
                           inventory_quantity INTEGER NOT NULL,
                           CONSTRAINT fk_inventory_product_id FOREIGN KEY (product_id)
                               REFERENCES product (product_id)
                               ON DELETE CASCADE
);
CREATE TABLE orders (
                        order_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
                        customer_id INTEGER,
                        order_date DATE DEFAULT CURRENT_DATE,
                        CONSTRAINT fk_order_customer_id FOREIGN KEY (customer_id)
                            REFERENCES customer (customer_id)
                            ON DELETE CASCADE
);
CREATE TABLE order_item (
                            order_item_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
                            product_id INTEGER,
                            order_item_quantity INTEGER NOT NULL,
                            order_id INTEGER,
                            CONSTRAINT fk_order_item_product_id FOREIGN KEY (product_id)
                                REFERENCES product (product_id)
--                                     ON DELETE CASCADE
    ,
                            CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id)
                                REFERENCES orders (order_id)
                                ON DELETE CASCADE
);

INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김일', '서울시', 'kim1@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김이', '부산시', 'kim2@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김삼', '대전시', 'kim3@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김사', '인천시', 'kim4@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김오', '대구시', 'kim5@gmail.com');

INSERT INTO product(product_name, product_description, product_price) VALUES ('제품1', '제품1설명', 10000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품2', '제품2설명', 20000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품3', '제품3설명', 30000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품4', '제품4설명', 40000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품5', '제품5설명', 50000);

INSERT INTO inventory VALUES (1, 1000);
INSERT INTO inventory VALUES (2, 2000);
INSERT INTO inventory VALUES (3, 3000);
INSERT INTO inventory VALUES (4, 4000);
INSERT INTO inventory VALUES (5, 5000);

INSERT INTO orders(customer_id) VALUES (1);
INSERT INTO orders(customer_id) VALUES (2);
INSERT INTO orders(customer_id) VALUES (3);
INSERT INTO orders(customer_id) VALUES (4);
INSERT INTO orders(customer_id) VALUES (5);

-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 1, 1);
-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 2, 1);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 3, 1);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 4, 2);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 5, 2);
-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 10, 3);
-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 20, 3);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 30, 4);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 40, 4);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 50, 4);
-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 100, 5);
-- INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 200, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 300, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 400, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 500, 5);

-- SELECT * FROM customer;
-- SELECT * FROM product;
-- SELECT * FROM inventory;
-- SElECT * FROM order_item;
-- SELECT * FROM orders;