insert into AUTHORS (ID, NAME) values (1, 'Harper Lee'),
                                      (2, 'Arthur Conan Doyle');
insert into GENRES (ID, GENRE) values(1, 'Novel'),
                                     (2, 'Detective');
insert into BOOKS (ID, NAME, AUTHOR_ID, GENRE_ID) values (1, 'To Kill a Mockingbird', 1, 1),
                                                         (2, 'A Study in Scarlet', 2, 2),
                                                         (3, 'The Sign of the Four', 2, 2);
