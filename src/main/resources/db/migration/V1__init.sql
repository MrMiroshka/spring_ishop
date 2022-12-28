
create table products
(
    id       bigserial,
    title varchar(30) not null,
    cost int not null,
    primary key (id)
);

insert into products(title, cost)
values ('Milk', 80),('Bread', 25),('Cheese', 300);
