--DROP TABLE IF EXISTS ROLES;
--CREATE TABLE ROLES(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    ROLE VARCHAR(255));
--
--DROP TABLE IF EXISTS USERS;
--CREATE TABLE USERS(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    USERNAME VARCHAR(255),
--    PASSWORD VARCHAR(255));
--
--DROP TABLE IF EXISTS USER_ROLES;
--CREATE TABLE USER_ROLES(
--    USER_ID   BIGINT,
--    ROLE_ID BIGINT,
--    FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE,
--    FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID),
--    UNIQUE KEY USER_ROLES_U1 (USER_ID, ROLE_ID));
--
--DROP TABLE IF EXISTS AUTHORS;
--CREATE TABLE AUTHORS(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    NAME VARCHAR(255));
--
--DROP TABLE IF EXISTS GENRES;
--CREATE TABLE GENRES(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    GENRE VARCHAR(255));
--
--DROP TABLE IF EXISTS COMMENTS;
--CREATE TABLE COMMENTS(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    COMMENT VARCHAR(255),
--    BOOK_ID BIGINT);
--
--DROP TABLE IF EXISTS BOOKS;
--CREATE TABLE BOOKS(
--    ID BIGINT IDENTITY PRIMARY KEY,
--    TITLE VARCHAR(255),
--    AUTHOR_ID BIGINT,
--    GENRE_ID BIGINT,
--    FOREIGN KEY (AUTHOR_ID)  REFERENCES AUTHORS (ID),
--    FOREIGN KEY (GENRE_ID)  REFERENCES GENRES (ID));
DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(
    ID BIGINT IDENTITY PRIMARY KEY,
    NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(
    ID BIGINT IDENTITY PRIMARY KEY,
    GENRE VARCHAR(255));

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
    ID BIGINT IDENTITY PRIMARY KEY,
    COMMENT VARCHAR(255),
    BOOK_ID BIGINT);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT IDENTITY PRIMARY KEY,
    TITLE VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT,
    FOREIGN KEY (AUTHOR_ID)  REFERENCES AUTHORS (ID),
    FOREIGN KEY (GENRE_ID)  REFERENCES GENRES (ID));
