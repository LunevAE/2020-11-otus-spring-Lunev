DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(
    ID BIGINT PRIMARY KEY,
    GENRE VARCHAR(255));

DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(
    ID BIGINT PRIMARY KEY,
    COMMENT VARCHAR(255),
    BOOK_ID BIGINT);

DROP SEQUENCE IF EXISTS BOOKS_SQ CASCADE;
CREATE SEQUENCE BOOKS_SQ START 3;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT DEFAULT nextval('BOOKS_SQ') PRIMARY KEY,
    TITLE VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT,
    FOREIGN KEY (AUTHOR_ID)  REFERENCES AUTHORS (ID),
    FOREIGN KEY (GENRE_ID)  REFERENCES GENRES (ID));