drop table if exists products10;
create table products10 (id bigint not null, name varchar(255), primary key (id));
INSERT INTO products10 (id, name) VALUES (1, 'Product A');
INSERT INTO products10 (id, name) VALUES (2, 'Product B');
INSERT INTO products10 (id, name) VALUES (3, 'Product C');
INSERT INTO products10 (id, name) VALUES (4, 'Product D');

drop table if exists students;
create table students (id bigint not null, name varchar(50), email varchar(50), phone varchar(15), primary key (id));
INSERT INTO students (id, name, email, phone) VALUES (1001, 'Student 1', 'Email 1', 'Phone 1');
INSERT INTO students (id, name, email, phone) VALUES (1002, 'Student 2', 'Email 2', 'Phone 2');
INSERT INTO students (id, name, email, phone) VALUES (1003, 'Student 3', 'Email 3', 'Phone 3');
