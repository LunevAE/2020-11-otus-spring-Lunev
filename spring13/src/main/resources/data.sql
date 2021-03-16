insert into ROLES(ID, ROLE) values
    (1, 'USER'),
    (2, 'ADMIN');

insert into USERS(ID, USERNAME, PASSWORD) values
    (1, 'user', '$2y$12$OJDeW6e5OfOS8VQQbIGxj.3/RTLGAOOnrd7uMbLmcUrLZrsphrkvy'),
    (2, 'admin', '$2y$12$TwOzv5Im1wdszgCbHfuhBOYYLu2F9KLISf4ic8xHUJYqVUzWPfAZ2');

insert into USER_ROLES(USER_ID, ROLE_ID) values
    (1, 1),
    (2, 2);

insert into AUTHORS (ID, NAME) values
    (1, 'Harper Lee'),
    (2, 'Arthur Conan Doyle');

insert into GENRES (ID, GENRE) values
    (1, 'Novel'),
    (2, 'Detective');

insert into BOOKS (ID, TITLE, AUTHOR_ID, GENRE_ID) values
    (1, 'To Kill a Mockingbird', 1, 1),
    (2, 'A Study in Scarlet', 2, 2);

insert into COMMENTS (ID, COMMENT, BOOK_ID) values
    (1, 'Nice', 1),
    (2, 'Great', 1);