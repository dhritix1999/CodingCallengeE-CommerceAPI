INSERT INTO products(id, product_name, unit_price)
VALUES (001, 'Rolex', 100),
       (002, 'Michael Kors', 80),
       (003, 'Swatch', 50),
       (004, 'Casio', 30);


INSERT INTO discounts(product_id, product_count, discount_bundle_price) VALUES
(001, 3, 200),
(002, 2, 120);