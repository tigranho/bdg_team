# 1.Գտնել այն մատակարաներին, որոնք մատակարարում են համակարգիչներ:
SELECT maker
FROM Product
WHERE type = 'PC';

# 2.Գտնել մատակարաներին և նրանց մատակարարած մոդելների քանակը:
SELECT maker, count(model)
FROM Product
GROUP BY maker;

# 3.Գտնել որ մատակարը քանի տեսակի ապրանք է մատակարարում:
SELECT maker, count(DISTINCT type)
FROM Product
GROUP BY maker;

# 4.Գտնել laptop-ների միջին գինը:
SELECT avg(price)
FROM Laptop;

# 5.Գտնել համակարգիչների կոդերը, մոդելները, արագությունները, գինը և մատակարարներին:
SELECT code, PC.model, speed, price, maker
FROM PC
         JOIN Product P ON PC.model = P.model;

# 6.Գտնել գունավոր տպիչների կոդը, մոդելները,գինն ու մատակարարին: Կարգավորել կոդերը ըստ գնի նվազման կարգով:
SELECT code, Printer.model, price, maker
FROM Printer
         LEFT JOIN Product ON Printer.model = Product.model
WHERE color = 'y'
ORDER BY price DESC;

# 7.Գտնել յուրաքանչյուր  մատակարարի մատակարարած համակարգիչների կոդերը, մոդելները, արագությունները, գինը: Գտնել նաև այն մոդելները, որոնց մասին ինֆորմացիա PC-ում չկա:
SELECT code, Product.model, speed, price
FROM Product
         LEFT JOIN PC ON Product.model = PC.model
WHERE type = 'PC';

# 8.Գտնել յուրաքանչյուր  մատակարարի մատակարարած համակարգիչների միջին գները: Այն մատակարարների համար, որոնց մատակարարած  համակարգիչների մասին ինֆորմացիա PC-ում չկա, միջին գին համարել 0-ն:
SELECT maker, IFNULL(AVG(price), 0)
FROM PC
         RIGHT JOIN Product ON PC.model = Product.model
WHERE type = 'PC'
GROUP BY maker;

# 9.Գտնել մատակարաներին, որոնք մատակարարում են և’ գունավոր տպիչ և’ PC:
SELECT maker
FROM Product
WHERE type = 'PC'
  AND maker IN (SELECT maker
                FROM Product
                         LEFT JOIN Printer ON Product.model = Printer.model
                WHERE Product.type = 'Printer'
                  AND color = 'y')
GROUP BY maker;

# 10.Գտնել մատակարաներին, որոնք մատակարարում են 500 և ավելի արագություն ունեցող laptop-ներ, բայց չեն արտադրում տպիչներ:
SELECT maker
FROM Product
         JOIN Laptop ON Product.model = Laptop.model
WHERE Laptop.speed >= 500
  AND maker NOT IN (SELECT maker FROM Product WHERE type = 'Printer');
