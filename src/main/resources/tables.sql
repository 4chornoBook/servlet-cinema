create table if not exists user_role
(
    role_id   serial primary key,
    role_name varchar(50) not null
);

create table if not exists cinema_user
(
    user_id  serial primary key,
    login    varchar(50)  not null unique,
    name     varchar(300) not null,
    surname  varchar(300) not null,
    password varchar(128) not null,
    salt     varchar(32)  not null,
    role_id  integer references user_role (role_id)
);


create table if not exists genre
(
    genre_id   serial primary key,
    genre_name varchar(100) not null
);


create table if not exists movie
(
    movie_id          serial primary key,
    name              varchar(300) not null,
    release_date      date         not null,
    description       text         not null,
    image_url         text         not null,
    ticket_price      numeric      not null,
    length_in_minutes integer      not null
);


create table if not exists movie_genre
(
    movie_id integer references movie (movie_id),
    genre_id integer references genre (genre_id),
    primary key (movie_id, genre_id)
);


create table if not exists movie_session
(
    session_id       serial primary key,
    movie_id         integer references movie (movie_id),
    available_places integer default 100,
    session_date     date                   not null,
    beginning_time   time without time zone not null,
    ending_time      time without time zone not null
);


create table if not exists order_status
(
    status_id   serial primary key,
    status_name varchar(100) not null
);


create table if not exists tickets_order
(
    order_id      serial primary key,
    user_id       integer references cinema_user (user_id),
    order_status  integer references order_status (status_id),
    creation_date timestamp not null
);


create table if not exists ticket
(
    ticket_id        serial primary key,
    place_number     integer not null,
    order_id         integer references tickets_order (order_id),
    movie_session_id integer references movie_session (session_id)
);


