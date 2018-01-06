create table employee (
    employee_number bigint primary key auto_increment,
    firstname varchar NOT NULL,
    lastname varchar NOT NULL,
    age int NOT NULL,
    hired_at date
);