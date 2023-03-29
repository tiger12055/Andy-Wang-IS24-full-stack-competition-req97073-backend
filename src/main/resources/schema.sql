DROP TABLE IF EXISTS product_developer;
DROP TABLE IF EXISTS DEVELOPER;
DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE PRODUCT (
    product_number INT PRIMARY KEY,
    product_name VARCHAR(255),
    scrum_master VARCHAR(255),
    product_owner VARCHAR(255),
    start_date DATE,
    methodology VARCHAR(255)
);

CREATE TABLE DEVELOPER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    developer_name VARCHAR(255)
);

CREATE TABLE product_developer (
    product_number INT,
    developer_id INT,
    PRIMARY KEY (product_number, developer_id),
    FOREIGN KEY (product_number) REFERENCES PRODUCT(product_number),
    FOREIGN KEY (developer_id) REFERENCES DEVELOPER(id)
);