CREATE TABLE usr (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR NOT NULL UNIQUE,
                       name VARCHAR NOT NULL,
                       password VARCHAR NOT NULL,
                       ad_author BOOLEAN NOT NULL
);

CREATE TABLE advertisement (
                     id SERIAL PRIMARY KEY,
                     brand VARCHAR NOT NULL,
                     model VARCHAR NOT NULL,
                     body_type VARCHAR NOT NULL,
                     created TIMESTAMP NOT NULL,
                     sold BOOLEAN NOT NULL,
                     user_id INTEGER REFERENCES usr,
                     image BYTEA
);