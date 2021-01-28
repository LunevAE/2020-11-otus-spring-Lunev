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
    NAME VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT,
    FOREIGN KEY (AUTHOR_ID)  REFERENCES AUTHORS (ID),
    FOREIGN KEY (GENRE_ID)  REFERENCES GENRES (ID));