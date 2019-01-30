create table employee (
    id bigint primary key auto_increment,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
);

create table department (
    department_id bigint primary key auto_increment,
    name varchar NOT NULL
);

create table purchase_order (
  id bigint primary key auto_increment,
  order_datetime datetime NOT NULL
);

create table order_detail (
  id bigint primary key auto_increment,
  purchase_order bigint NOT NULL,
  quantity int NOT NULL,
  item varchar NOT NULL
);
