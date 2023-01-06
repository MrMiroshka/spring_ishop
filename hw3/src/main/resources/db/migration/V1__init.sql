
create table products
(
    id       bigserial,
    title varchar(255) not null,
    cost int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);

create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(80) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

create table orders
(
    id   bigserial primary key ,
    user_id bigint not null references users (id),
    total_price int not null,
    address varchar(255),
    phone varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

create table order_items
(
    id   bigserial primary key ,
    product_id bigint not null references products (id),
    order_id bigint not null references orders (id),
    quantity int not null,
    price_per_product int not null,
    price int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp

);

create table authority
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);


create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table users_authority
(
    user_id bigint not null,
    authority_id int    not null,
    primary key (user_id, authority_id),
    foreign key (user_id) references users (id),
    foreign key (authority_id) references authority (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into authority (name)
values ('OP_READ'),
       ('OP_EDIT');

insert into users ( username, password, email)
values ('user', '$2a$10$5KpQnigJwmRuya4JAE9Vz.YaO9MZ4QnZ48S8Ng33Ja7J0gtGq3DOS', 'test@yandex.ru');

insert into users_roles(user_id, role_id)
values (1, 1);

insert into users_authority(user_id, authority_id)
values (1, 1);



insert into products(title, cost)
values ('Milk', 80),('Bread', 25),('Cheese', 300);
