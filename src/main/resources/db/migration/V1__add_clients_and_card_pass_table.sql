drop table if exists clients;
drop table if exists card_pass;

create table clients
(
    id      serial      not null,
    name    varchar(30) not null,
    surname varchar(30) not null,
    age     integer     not null,
    email   varchar(50)
);

create table card_pass
(
    id    serial  not null,
    start_date date    not null,
    end_date   date    not null,
    level integer not null
);
