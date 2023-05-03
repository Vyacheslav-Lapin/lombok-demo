-- set mode postgresql;

drop table if exists Person;
create table Person
(
    id         int generated always as identity,
    first_name varchar(255) not null,
    last_name  varchar(255),
    permission boolean default false,
    dob        date,
    email      varchar(255) not null,
    password   varchar(255) not null,
    address    varchar(255),
    telephone  varchar(15),
    primary key (id)
);
