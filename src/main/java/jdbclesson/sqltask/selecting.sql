#1
select maker
from product where type = 'pc';

#2
select maker, count(model)
from product group by maker;

#3
select maker, count(distinct type) from product group by maker;

#4
select avg(price) from laptop;

#5
select pc.code, pc.speed, pc.price, maker, pc.model
from pc left outer join product p on pc.model = p.model;

#6
select code, color, printer.model, price, maker
from printer join product p on p.model = printer.model where color = 'y' order by price;

#7
select pc.code, pc.model, pc.speed, pc.price, maker from pc join product p on p.model = pc.model;

#8
select maker, avg(price) from pc left join product p on p.model = pc.model;

#9
####
####

#10
select maker, speed from laptop inner join product p on p.model = laptop.model where speed >= 500;