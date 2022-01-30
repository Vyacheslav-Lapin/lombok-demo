set mode postgresql;

create table Person
(
    id         int primary key auto_increment,
    first_name varchar(255) not null,
    last_name  varchar(255),
    permission boolean default false,
    dob        date,
    email      varchar(255) not null,
    password   varchar(255) not null,
    address    varchar(255),
    telephone  varchar(15)
);
