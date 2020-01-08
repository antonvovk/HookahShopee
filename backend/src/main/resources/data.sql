INSERT INTO POST
VALUES (N'Мережа магазинів “Залупка” - це найкращий та найдешевший спосіб придбати табак та комплектуючі для кальяну. і тд. Для Вашої уваги досутпні преміальні мрки табаку за найнижчими цінами. На нашому сайті ви можете знайти: ● Milano ● Adalya ● Dark Side ●',
        'Post1.png', N'Відтепер ми також в Івано-Франківську')

INSERT INTO CITY
VALUES ('Lviv');
INSERT INTO CITY
VALUES ('Ivano-frankivsk');

INSERT INTO MANUFACTURER
VALUES ('Adalya.png', 'Adalya');
INSERT INTO MANUFACTURER
VALUES ('Jibiar.png', 'Jibiar');
INSERT INTO MANUFACTURER
VALUES ('Serbetli.png', 'Serbetli');
INSERT INTO MANUFACTURER
VALUES ('Milano.png', 'Milano');

INSERT INTO USR
VALUES ('Dankovych', 'Bohdan', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380963587506',
        'ADMIN', 1);
INSERT INTO USR
VALUES ('Dankovych', 'Tanya', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380970362116', 'SELLER',
        1);
INSERT INTO USR
VALUES ('Taras', 'Kalik', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380958674875', 'SELLER', 2);

INSERT INTO USR
VALUES ('Vovk', 'Anton', '$2y$12$hdOskOQGvuBxznq3J0ZJZusl8NGQYX1cV6gkMVVdTiE1ih3QjT4My', '380965876896', 'CLIENT', 1);

INSERT INTO PRODUCT
VALUES (N'<p><strong style="color: rgb(61, 20, 102);">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Milano</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Adalya </strong></p><p><strong style="color: rgb(61, 20, 102);">● Dark Side </strong></p><p><br></p>',
        0, 75, null, 'Adalya Blue Peach Mint', 75, 1);
INSERT INTO PRODUCT
VALUES (N'<p><strong style="color: rgb(61, 20, 102);">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Milano</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Adalya </strong></p><p><strong style="color: rgb(61, 20, 102);">● Dark Side </strong></p><p><br></p>',
        15, 115, null, 'JIBIAR Baked Peach Spiced', 130, 2);
INSERT INTO PRODUCT
VALUES (N'<p><strong style="color: rgb(61, 20, 102);">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Milano</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Adalya </strong></p><p><strong style="color: rgb(61, 20, 102);">● Dark Side </strong></p><p><br></p>',
        6, 54, null, 'Serbetli Toasted Berry', 60, 3);
INSERT INTO PRODUCT
VALUES (N'<p><strong style="color: rgb(61, 20, 102);">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Milano</strong></p><p><strong style="color: rgb(61, 20, 102);"> ● Adalya </strong></p><p><strong style="color: rgb(61, 20, 102);">● Dark Side </strong></p><p><br></p>',
        6, 54, null, 'Serbetli Baja Blue', 60, 3);

INSERT INTO PRODUCT_ITEM
VALUES (12, 1, 1);
INSERT INTO PRODUCT_ITEM
VALUES (10, 1, 2);
INSERT INTO PRODUCT_ITEM
VALUES (40, 2, 1);
INSERT INTO PRODUCT_ITEM
VALUES (5, 3, 2);
INSERT INTO PRODUCT_ITEM
VALUES (10, 4, 2);

INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'd9da2769-e280-4f9f-9e39-227f20485a27', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'e10ceeca-c7de-4862-a4ec-d41bf671fc58', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'e364999b-b7b6-4cbd-b2cd-47665d64fa8b', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', '946d9ece-fb2a-41ba-9b7c-b9ca63c2b8bb', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'e0bf01e1-2e76-47db-99e8-d8647c96c123', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', '89b0ccae-713b-4bad-aa07-4f09b10a59bf', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', '1c67a9ec-de22-4b61-ac4d-85d96be11bcd', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'a4a66dc9-b51a-45fb-9b9b-750ff1aae78b', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', 'cfa34bb2-627c-44f6-840f-4f5f77e1c8b9', 4, null);
INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'NEW', '08d13ea7-20c8-4bb9-9ce4-0077d0f03623', 4, null);

INSERT INTO ORDER_ITEM
VALUES (250, 20, 1, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 1, 1)

INSERT INTO ORDER_ITEM
VALUES (250, 20, 2, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 2, 1)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 3, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 3, 1)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 4, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 4, 1)

INSERT INTO ORDER_ITEM
VALUES (250, 20, 5, 2)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 5, 2)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 6, 3)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 6, 3)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 7, 3)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 7, 3)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 8, 4)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 8, 4)


INSERT INTO ORDER_ITEM
VALUES (250, 20, 9, 2)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 9, 2)

INSERT INTO ORDER_ITEM
VALUES (250, 20, 10, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 10, 1)


INSERT INTO ORDR
VALUES (null, 500, '2019-06-25 12:00:00.0000000', 'IN_PROGRESS', '7084e708-105b-4c78-b2b3-adffbc06e1cf', 4, 1);
INSERT INTO ORDR
VALUES ('2019-06-25 19:21:00.0000000', 500, '2019-06-25 12:00:00.0000000', 'COMPLETED',
        '7fe72dd4-b4c9-40c7-af88-24665bb8dd45', 4, 1);

INSERT INTO ORDER_ITEM
VALUES (250, 20, 11, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 11, 1)

INSERT INTO ORDER_ITEM
VALUES (250, 20, 12, 1)
INSERT INTO ORDER_ITEM
VALUES (250, 20, 12, 1)
