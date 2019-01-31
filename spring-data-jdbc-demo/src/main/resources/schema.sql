create table employee (
    id bigint primary key auto_increment,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
);

create table department (
    id bigint primary key auto_increment,
    name varchar NOT NULL
);

create table department_employee (
    department bigint NOT NULL,
    employee bigint NOT NULL,
    primary key(department, employee)
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


create table book (
  id int primary key auto_increment,
  title varchar NOT NULL
);

create table author (
  id int primary key auto_increment,
  name varchar NOT NULL
);

create table book_author (
  book int NOT NULL,
  author int NOT NULL,
  primary key (book, author)
);
