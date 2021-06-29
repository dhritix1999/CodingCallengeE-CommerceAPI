create
sequence sequence_generator start
with 1 increment by 50;

create table products
(
    id           varchar(100) not null,
    product_name varchar(100) not null,
    unit_price   double       not null,
    primary key (id)
);

create table discounts
(
    product_id            varchar(100) not null,
    product_count         long         not null,
    discount_bundle_price double       not null,
    primary key (product_id)
);