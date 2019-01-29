create table employee (
    employee_number bigint primary key auto_increment,
    firstname varchar NOT NULL,
    lastname varchar NOT NULL,
    age int NOT NULL,
    hired_at date,
    department bigint
);

create table department (
    department_id bigint primary key auto_increment,
    name varchar NOT NULL
);

create table purchase_order (
  id bigint primary key auto_increment,
  shipping_address varchar NOT NULL
);

create table order_item (
  id bigint primary key auto_increment,
  purchase_order bigint NOT NULL,
  quantity int NOT NULL,
  product varchar NOT NULL
);