MERGE INTO GENRE (GENRE_ID, NAME) VALUES (1, 'Comedy');
MERGE INTO GENRE (GENRE_ID, NAME) VALUES (2, 'Drama');
MERGE INTO GENRE (GENRE_ID, NAME) VALUES (3, 'Animation');
MERGE INTO GENRE (GENRE_ID, NAME) VALUES (4, 'Thriller');
MERGE INTO GENRE (GENRE_ID, NAME) VALUES (5, 'Documentary');
MERGE INTO GENRE (GENRE_ID, NAME) VALUES (6, 'Action');

MERGE INTO MPA_RATE (RATE_ID, NAME) VALUES (1, 'G');
MERGE INTO MPA_RATE (RATE_ID, NAME) VALUES (2, 'PG');
MERGE INTO MPA_RATE (RATE_ID, NAME) VALUES (3, 'PG-13');
MERGE INTO MPA_RATE (RATE_ID, NAME) VALUES (4, 'R');
MERGE INTO MPA_RATE (RATE_ID, NAME) VALUES (5, 'NC-17');