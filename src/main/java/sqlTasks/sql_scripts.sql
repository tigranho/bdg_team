use task1;
#1
SELECT maker
FROM product
WHERE type = 'PC';

#2
SELECT COUNT(model), maker
FROM product
GROUP BY maker;

#3
SELECT COUNT(DISTINCT type), maker
FROM product
GROUP BY maker;


#4
SELECT avg(price)
FROM laptop;

#5
SELECT *
FROM PC
LEFT JOIN product
ON PC.model = product.model;

#6
SELECT *
FROM printer
LEFT JOIN product
ON printer.model = product.model
WHERE printer.color = 'y'
ORDER BY code desc;

#7
SELECT maker, code, product.model, PC.model, speed, price
FROM product
lEFT JOIN PC
ON product.model = PC.model;

#8
SELECT maker, COALESCE(AVG(price), 0)
FROM product
LEFT JOIN PC
ON product.model = PC.model
GROUP BY maker;

#9
SELECT DISTINCT maker
FROM product
WHERE type = 'PC' AND maker IN
 (SELECT DISTINCT maker
 FROM product
 LEFT JOIN printer ON product.model = printer.model
 WHERE product.type = 'printer' AND printer.color = 'y'
 );

 #10
 SELECT DISTINCT maker
 FROM product
 INNER JOIN laptop ON product.model = laptop.model
 WHERE laptop.speed >= 500  AND maker
 NOT IN (SELECT maker
 FROM product
 WHERE type = 'printer');

