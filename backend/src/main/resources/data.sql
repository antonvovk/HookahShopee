INSERT INTO CITY
VALUES ('Lviv');
INSERT INTO CITY
VALUES ('Ivano-frankivsk');

INSERT INTO MANUFACTURER
VALUES ('Adalya');
INSERT INTO MANUFACTURER
VALUES ('Jibiar');
INSERT INTO MANUFACTURER
VALUES ('Serbetli');
INSERT INTO MANUFACTURER
VALUES ('Milano');

INSERT INTO SHOP
VALUES ('bohdan.dankovych@gmail.com', '380963587506', 'Monday - Friday: 8:00 - 22:00', 1);
INSERT INTO SHOP
VALUES ('bohdan.dankovych@gmail.com', '380963587506', 'Monday - Friday: 8:00 - 22:00', 2);

INSERT INTO SELLER
VALUES ('Dankovych', 'Bohdan', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380963587506',
        'SUPER_ADMIN', 1);
INSERT INTO SELLER
VALUES ('Dankovych', 'Tanya', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380970362116', 'ADMIN',
        1);
INSERT INTO SELLER
VALUES ('Taras', 'Kalik', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380958674875', 'ADMIN', 2);

INSERT INTO CLIENT
VALUES ('Vovk', 'Anton', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380963587506', 'CLIENT', 1);

INSERT INTO PRODUCT
VALUES ('Good shit', 0, 75, 'Adalya Blue Peach Mint', 75, 1);
INSERT INTO PRODUCT
VALUES ('Great tabaco', 15, 115, 'JIBIAR Baked Peach Spiced', 130, 2);
INSERT INTO PRODUCT
VALUES ('Laolok dfsfs fsd', 6, 54, 'Serbetli Toasted Berry', 60, 3);
INSERT INTO PRODUCT
VALUES ('Lorem ispum de altio', 6, 54, 'Serbetli Baja Blue', 60, 3);

INSERT INTO PRODUCT_ITEM
VALUES (12, 1, 1);
INSERT INTO PRODUCT_ITEM
VALUES (40, 2, 1);
INSERT INTO PRODUCT_ITEM
VALUES (5, 3, 2);
INSERT INTO PRODUCT_ITEM
VALUES (10, 4, 2);
