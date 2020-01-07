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
