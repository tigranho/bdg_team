SELECT maker FROM Product WHERE type='PC';

SELECT maker, count(model) FROM Product GROUP BY maker;

SELECT maker, count(DISTINCT type) FROM Product GROUP BY maker;

SELECT avg(price) FROM Laptop;

SELECT code, PC.model, speed, price, maker
FROM PC JOIN Product P ON PC.model = P.model;

SELECT code, Printer.model, price, maker
FROM Printer JOIN Product ON Printer.model = Product.model
WHERE color='y'
ORDER BY price DESC ;

SELECT code, Product.model, speed, price
FROM Product LEFT JOIN PC ON Product.model = PC.model;

SELECT maker, AVG(IFNULL(price, 0))
FROM PC RIGHT JOIN Product ON PC.model = Product.model
GROUP BY maker;

SELECT maker FROM Product
WHERE type='PC' OR type='Printer' GROUP BY maker;

SELECT maker
FROM (SELECT * FROM Product WHERE type != 'Printer') AS P
         JOIN Laptop ON P.model = Laptop.model
WHERE Laptop.speed >= 500
GROUP BY maker;
