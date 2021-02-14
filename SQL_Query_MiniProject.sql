create table customer(fname varchar(30) not null,lname varchar(40) not null,address varchar(100) not null,city varchar(50) not null,pin varchar(10) not null,state varchar(30) not null,
user_class varchar(50) not null,phase varchar(20) not null,meter_no varchar(20) not null,adhar varchar(20) not null,email varchar(30),deposit varchar(10),phone varchar(10) not null,
date1 date not null, primary key(adhar,meter_no));

create table login(category varchar(30) not null, user varchar(50) not null, password varchar(50) not null);


create table bill(meter_no varchar(20),bill_id int,from_date date,to_date date,units varchar(10),amount varchar(30),phase varchar(30),category varchar(50),extra_charge double,
 payment varchar(10),month_charge double, PRIMARY KEY(bill_id,to_date,meter_no));


create table users(fname varchar(30) not null, lname varchar(30),username varchar(50) not null, email varchar(5),password varcchar(50) not null,cpassword varchar(50) not null,
 category varchar(40));

create table tax(id int PRIMARY KEY,meter_type varchar(30) REFERENCES customer(user_class),phase varchar(10) ,bill_type varchar(20),days int,meter_rent int,mcb_rent int,
service_rent int, gst int);

create table city(city varchar(30),id int primary key);  

create table complaint(id varchar(200) primary key,meter_no varchar(30),complaints varchar(300),city varchar(20) REFERENCES city(city));

CREATE TABLE payment( date1 VARCHAR(20) NOT NULL ,meter_no  VARCHAR(30) NOT NULL REFERENCES customer(meter_no));













insert into tax values(1,'Residential+Commercial','1-Phase','Two Part T',30,50,12.15,150.06,150);
insert into tax values(2,'Residential+Commercial','3-Phase','Two Part T',30,50,12.15,200.76,150);
insert into tax values(3,'Residential','1-Phase','Two Part T',30,50,12.15,80.56,150);
insert into tax values(4,'Residential','3-Phase','Two Part T',30,50,12.15,90.36,150);
insert into tax values(5,'Commercial','1-Phase','Two Part T',30,50,12.15,102.36,150);
insert into tax values(6,'Commercial','3-Phase','Two Part T',30,50,12.15,106.36,150);

ALTER TABLE bill
ADD extra_charge double;

ALTER TABLE bill
ADD payment varchar(3);

CREATE TABLE payment( date1 VARCHAR(20) NOT NULL ,meter_no  VARCHAR(30) PRIMARY KEY);
INSERT INTO payment values('2020-12-01','1000');
INSERT INTO payment values('2020-12-01','1001');
INSERT INTO payment values('2020-12-01','1002');
INSERT INTO payment values('2020-09-29','1003');
INSERT INTO payment values('2020-12-01','1004');
INSERT INTO payment values('2020-12-01','1005');
INSERT INTO payment values('2020-11-30','1006');
INSERT INTO payment values('2020-12-01','1007');
INSERT INTO payment values('2020-10-28','1008');
INSERT INTO payment values('2020-12-01','1009');



drop table bill;
create table bill(meter_no varchar(20) REFERENCES customer(meter_no),bill_id int,from_date date,to_date date,units varchar(10),amount varchar(30),phase varchar(30),category varchar(50),extra_charge double, payment varchar(10),month_charge double, PRIMARY KEY(bill_id,to_date));


drop table bill;
create table bill(meter_no varchar(20),bill_id int,from_date date,to_date date,units varchar(10),amount varchar(30),phase varchar(30),category varchar(50),extra_charge double, payment varchar(10),month_charge double, PRIMARY KEY(bill_id,to_date,meter_no));


