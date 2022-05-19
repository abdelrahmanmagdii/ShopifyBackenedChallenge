DROP TABLE IF EXISTS CITY;
CREATE TABLE CITY (
      code INT AUTO_INCREMENT  PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      pincode INT(8) NOT NULL
);
DROP TABLE IF EXISTS product;
-- AUTO_INCREMENT
CREATE TABLE product (
                         sku INT   PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         description VARCHAR(100) NOT NULL,
                         category VARCHAR(50) NOT NULL

);

DROP TABLE IF EXISTS warehouse;
CREATE TABLE warehouse (
                           id INT  PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           country VARCHAR(50) NOT NULL,
                           state VARCHAR(50) NOT NULL,
                           zipcode VARCHAR(10) NOT NULL


);
--
DROP TABLE IF EXISTS product_warehouse;
CREATE TABLE product_warehouse (
                           product_sku INT   PRIMARY KEY,
                           warehouse_id INT  NOT NULL,
                           product_quantity INT NOT NULL

);

ALTER TABLE product_warehouse
    ADD CONSTRAINT fk1_product_warehouse
        FOREIGN KEY (product_sku)
            REFERENCES product(sku)
            ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE product_warehouse
    ADD CONSTRAINT fk2_product_warehouse
        FOREIGN KEY (warehouse_id)
            REFERENCES warehouse(id)
            ON DELETE CASCADE ON UPDATE NO ACTION;











