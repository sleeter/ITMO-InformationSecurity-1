CREATE TABLE users (
                       id bigserial primary key ,
                       name varchar(30) not null,
                       password text not null,
                       created_at timestamp default current_timestamp
);

CREATE TABLE posts (
    id bigserial primary key,
    description text not null,
    user_id int references users(id)
);