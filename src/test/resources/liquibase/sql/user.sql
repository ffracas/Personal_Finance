--liquibase formatted sql

--changeset aspeeencinaf:11
INSERT INTO USERS(id, name, email, password_hash, creation_date)
VALUES ('1658cc09-ef5d-4f5b-8fe8-d9f7abfbfbec', 'phil', 'thephilbestmail@test', 'djskaljdqwlkdluik23',
        CURRENT_TIMESTAMP)