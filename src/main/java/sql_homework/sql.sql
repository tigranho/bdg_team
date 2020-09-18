#1
SELECT maker
FROM product
WHERE type = 'PC'
GROUP BY maker;

#2
SELECT maker, count(model) AS 'Product count'
FROM product
GROUP BY maker;

#3
SELECT maker, count(DISTINCT type) AS 'Type count'
FROM product
GROUP BY maker;

#4
SELECT AVG(price) AS 'Laptop average price'
FROM laptop;

#5
SELECT code, pc.model, speed, price, maker
FROM pc
         INNER JOIN product ON pc.model = product.model;

#6
SELECT code, printer.model, price, maker
FROM printer
         LEFT JOIN product ON printer.model = product.model
WHERE color = 'y'
ORDER BY price DESC;

#7
SELECT maker, code, product.model, speed, price
FROM pc
         RIGHT JOIN product ON product.model = pc.model
WHERE type = 'PC';

#8
SELECT maker, IFNULL(AVG(price), 0)
FROM product
         LEFT JOIN pc ON product.model = pc.model
WHERE type = 'pc'
GROUP BY maker;

#9
SELECT DISTINCT maker
FROM pc
         INNER JOIN product p ON pc.model = p.model
WHERE maker IN (SELECT DISTINCT maker
                FROM product pd
                         INNER JOIN printer pr ON pr.model = pd.model
                WHERE color = 'y');

#10
SELECT DISTINCT maker
FROM laptop l
         INNER JOIN product p ON l.model = p.model
WHERE speed >= 500
  AND maker NOT IN (SELECT maker FROM product WHERE type = 'printer');