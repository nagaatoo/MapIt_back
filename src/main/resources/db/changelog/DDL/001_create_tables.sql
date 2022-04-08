--liquibase formatted sql
--changeset techgeeknext:create-tables

CREATE TABLE users (
                         id bigint PRIMARY KEY,
                         nick_name varchar(255) NOT NULL UNIQUE ,
                         password varchar(255) NOT NULL,
                         sex bool NOT NULL,
                         fio varchar(255),
                         created date,
                         birthday date
);
CREATE SEQUENCE user_seq START 2;
insert into users (id, fio, nick_name, password, sex) values (1, 'foo', 'admin', '$2a$10$ijT1sp7BekiHaqFTFMntxuzLigUBAabM9WWy/jIvoZsqQRy8ms.ae', true);

CREATE TABLE friends (
                        id bigint PRIMARY KEY,
                        user_id bigint NOT NULL UNIQUE REFERENCES users (id),
                        friend_id bigint NOT NULL REFERENCES users (id)
);
CREATE SEQUENCE friends_seq START 1;

CREATE TABLE point (
                       id bigint PRIMARY KEY,
                       longitude varchar(255) NOT NULL,
                       latitude varchar(255) NOT NULL,
                       user_id bigint NOT NULL REFERENCES users (id)
);
CREATE SEQUENCE point_seq START 1;

CREATE TABLE files (
                       id bigint PRIMARY KEY,
                       file_id varchar(255) UNIQUE NOT NULL,
                       point_id bigint REFERENCES point (id)
);
CREATE SEQUENCE files_seq START 1;