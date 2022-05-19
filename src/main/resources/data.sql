INSERT INTO CITY VALUES (12, 'Kanpur', 208001);
INSERT INTO CITY VALUES (13, 'Lucknow', 226001);
INSERT INTO PRODUCT VALUES (11, 'XYZ Headphones', 'AUX headphones for gaming', 'Electronics');
INSERT INTO PRODUCT VALUES (28, 'My Mechanical Keyboard', 'Great mechanical keyboard for office spaces', 'Electronics');
INSERT INTO PRODUCT VALUES (13, 'ZYX Laser Printer', 'Affordable Laser printer made for the office, have them go crazy printing!', 'Electronics');
INSERT INTO PRODUCT VALUES (66, 'Desk Chair by OFFICE INC', 'Comfortable desk chair for your office', 'Office');
INSERT INTO WAREHOUSE VALUES (20, 'Mexican Warehouse', 'Mexico', 'New Mexico City', 'M15 6B4');
INSERT INTO WAREHOUSE VALUES (13, 'Canadian Warehouse', 'Canada', 'Ontario', 'C1A 6D6');
INSERT INTO PRODUCT_WAREHOUSE VALUES (11, 20, 5);
INSERT INTO PRODUCT_WAREHOUSE VALUES (66, 20, 5);


--
-- DROP TABLE IF EXISTS product_warehouse;
-- CREATE TABLE product_warehouse (
--                                    product_sku INT   PRIMARY KEY,
--                                    warehouse_id INT  NOT NULL,
--                                    product_quantity INT NOT NULL
