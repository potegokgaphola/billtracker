# Billtracker
Billtracker is a desktop application that you can use to manage your day to day bills. Currently you can log a bill, view a bill, and generate a line graph for a specific bill type(food, water, electricity). You can only log one bill for a day for each type.

## Database
This application uses a database to store its information.
The `DDL` script to create the database is below:
```sql
create table users (
    user_id bigint auto_increment not null primary key,
    username varchar(100) not null,
    firstname varchar(100) not nullL,
    lastname varchar(100) not null,
    password varchar(100) not null
);

create table bills (
    bill_id bigint auto_increment not null primary key,
    bill_amount double not null,
    `date` date not null,
    bill_type varchar(50) not null,
    user_id bigint not null,
    foreign key fk_bills_users (user_id) references users (user_id)
);
``` 
