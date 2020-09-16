#1
SELECT maker FROM product WHERE type = 'PC' GROUP BY maker;

#2
SELECT maker, count(model) AS 'Product count' FROM product GROUP BY maker;

#3
SELECT maker, count(DISTINCT type) AS 'Type count' FROM product GROUP BY maker;

#4
SELECT AVG(price) AS 'Laptop average price' FROM laptop;

#5
SELECT code, pc.model, speed, price, maker  FROM pc
INNER JOIN product ON pc.model = product.model;

#6
SELECT code, printer.model, price, maker FROM printer
INNER JOIN product ON printer.model = product.model
WHERE color = 'y'
ORDER BY price DESC;

#7
SELECT maker, code, product.model, speed, price FROM pc
RIGHT JOIN product ON product.model = pc.model
WHERE type = 'PC';

#8
SELECT maker, AVG(price) FROM product
INNER JOIN pc ON product.model = pc.model
WHERE type = 'pc'
GROUP BY maker;