--1
select maker from Product where type = 'PC';
--2
select maker, count(model) from Product group by maker;
--3
select maker, count(distinct type) from Product group by maker;
--4
select avg(price) from Laptop;
--5
select code, pc.model, speed, price, maker from PC
    left join product on pc.model = product.model;
--6
select code, printer.model, price, maker from printer
    left join product on printer.model = product.model
order by code desc;
--7
select maker, pc.code, pc.model, pc.speed, pc.price from product
    full outer join pc on product.model = pc.model;
--8
select maker,  COALESCE(avg(pc.price), 0) from product
    left join pc on product.model = pc.model
group by maker
--9
select distinct maker from product
where type = 'PC' and maker in (
    select distinct maker from product where type = 'Printer'
);
--10
select distinct maker from product
    inner join laptop l on product.model = l.model and l.speed > 500
where maker not in (
    select maker from product where product.type = 'Printer'
);