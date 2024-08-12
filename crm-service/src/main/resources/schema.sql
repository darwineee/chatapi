create table if not exists Clients (
    id serial primary key,
    public_id varchar(30) not null,
    name varchar(50) not null,
    master_api_token char(36) not null
);

create table if not exists Users (
    id serial primary key,
    name varchar(50) not null,
    client_id int references Clients(id)
    on delete cascade
    on update cascade
);

create table if not exists Channels (
    id serial primary key,
    name varchar(100) not null,
    client_id int references Clients(id)
    on delete cascade
    on update cascade
);

create table if not exists UserChannel (
    id serial primary key,
    user_id int references Users(id)
    on delete cascade
    on update cascade,
    channel_id int references Channels(id)
    on update cascade
    on delete cascade
);