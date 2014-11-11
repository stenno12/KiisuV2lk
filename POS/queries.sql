select stockitem.name, sum(quantity) total_quantity, stockitem.price, (sum(quantity)*stockitem.price) total_income
from solditem inner join stockitem on solditem.stockitem_id = stockitem.id
group by solditem.stockitem_id, stockitem.name, stockitem.price
order by total_income desc;

select i_date from (
select stockitem.name, sum(quantity) total_quantity, stockitem.price, (sum(quantity)*stockitem.price) total_income, solditem.sale_date i_date
from solditem inner join stockitem on solditem.stockitem_id = stockitem.id
group by solditem.stockitem_id, stockitem.name, stockitem.price, solditem.sale_date
order by total_income desc)
limit 1;