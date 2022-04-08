insert into user_role
values (default, 'admin'),
       (default, 'user');

insert into cinema_user
values (default, 'user1,', 'Don', 'Acardion', '�b��~D�K�!�d�]5', 'c5*���ʴ�R`f(�', 1),-- admin name: user1, password: user1
       (default, 'userX', 'Kaneki', 'Ken', 'n�3Ұƴ�R�<�W', '[����9n�tL�', 2),--user name: userX, password: userX
       (default, 'user10', 'Satoshi', 'Nakamoto', 'J���Q�٪��rFٕ�', 'g����C.���Z�L�8�',
        2); --user name:user10, password: user10

insert into genre
values (default, 'Комедія'),
    1(default, 'Романтика'),
       2(default, 'Наукова фантастика'),
       3(default, 'Жахи'),
       4(default, 'Документальний фільм'),
       5(default, 'Бойовик'),
       6(default, 'Триллер'),
       7(default, 'Драма'),
       8(default, 'Комедія'),
       9(default, 'Пригода'),
       10(default, 'Аніме'),
       11(default, 'Утопія'),
       12(default, 'Антиутопія'),
       13(default, 'Вестерн'),
       14(default, 'Детектив'),
       15(default, 'Постапокаліпсис'),
       16(default, 'Біографічний фільм'),
       17(default, 'Музика'),
       18(default, 'Космос'),
       19(default, 'Мультфільм'),
        20(default,'Фантастика');

insert into movie
values (default,
        'Назад у майбутнє',
        '"1985-07-03"',
        '«Назад у майбутнє» — це історія про підлітка, що випадково потрапив із 1985 в 1955 рік. Він натрапляє на своїх батьків, учнів середньої школи, й випадково пробуджує романтичний інтерес з боку своєї матері. Марті мусить компенсувати завдану шкоду історії, примусивши своїх батьків покохати одне одного, а також має знайти спосіб повернутися назад у 1985 рік.',
        'https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg',
        116),
       (default,
        'Зелена книга',
        '2019-01-24',
        'У 1962 році викидайла італо-американського походження Тоні Валлелонга на прізвисько «Тоні Балакун» (Вігго Мортенсен) наймають шофером на час туру відомого афроамериканського джазового піаніста Дона Ширлі (Магершала Алі). За угодою, Тоні отримає повний обсяг гонорару у тому випадку, якщо Дон вчасно прибуде і відіграє усі заплановані концерти. Усі, окрім останнього відбулись, однак Дон сам вирішив не виступати для публіки, що забороняє йому їсти разом з ними. Абсолютно різні, вони разом подорожують провінційним півднем США. Дорогою вони користуються так званою «Зеленою книгою негритянського автомобіліста» Віктора Г''юґо Ґріна, що містить інформацію про безпечні місця для афроамериканців. Ця подорож розкриє їм очі на багато речей, що відбуваються у світі навколо них.',
        'https://uainfo.org/static/ckef/img/!!!_29.jpg',
        130),
       (default,
        'Богемна рапсодія',
        '2018-11-01',
        'У 1970 році в Лондоні Фаррух "Фредді" Булсара, біженець-парс із Занзібару, працює вантажником багажу в аеропорту Хітроу і живе з батьками та сестрою. Одного разу Фаррух вирушає до пабу та потрапляє на виступ студентської рок-групи "Smile". Гурт так його здивував, що він вирушає на вулицю шукати музикантів, і врешті, знайомиться з барабанщиком Роджером Тейлором та гітаристом Браяном Меєм. Дізнавшись від них про вихід з групи свого вокаліста-басиста Тіма Стаффелла, Фаррух пропонує взяти його як вокаліста і Роджер з Браяном приймають його до гурту.',
        '"https://m.media-amazon.com/images/M/MV5BMTA2NDc3Njg5NDVeQTJeQWpwZ15BbWU4MDc1NDcxNTUz._V1_.jpg"',
        134),
       (default,
        'Інтерстеллар',
        '2014-11-05',
        'Події фільму починаються в недалекому майбутньому — Земля вже не в змозі підтримувати людство, пилові бурі винищують ґрунти, відбувається регрес сучасного суспільства до аграрного. Купер (Меттью Макконехі), колишній льотчик-випробувач NASA і інженер, повертається на ферму до своєї сім''ї: сина Тома, 10-річної дочки Мерфі та батька його померлої дружини Дональда. Мерфі вважає, що в їхньому будинку є привид, який намагається спілкуватися з нею. Купер бачить, як Мерфі намагається науково обґрунтувати існування примари, та несподіванно виявляє, що «привид» — це невідома форма інтелекту, яка відправляє їм закодовані повідомлення за допомогою гравітаційної аномалії — змінюючи пил на підлозі у вигляді візерунка, який нагадує азбуку Морзе. Повідомлення стосуються секретного проекту NASA на чолі з професором Брендом (Майкл Кейн). Купер розшукує професора і зустрічається з ним.',
        'https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg',
        169),
       (default,
        'Тенет',
        '2020-08-26',
        'Озброєний єдиним словом — Тенет — головний герой бореться за виживання усього світу. Він подорожує оповитим сутінками світом міжнародного шпигунства з єдиною місією, яка розгорнеться за межами реального часу. Це не подорож крізь час. Це інверсія.',
        'https://m.media-amazon.com/images/M/MV5BYzg0NGM2NjAtNmIxOC00MDJmLTg5ZmYtYzM0MTE4NWE2NzlhXkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_.jpg',
        150),
       (default,
        'Дюна: Частина перша',
        '2021-10-01',
        'Шляхетний дім Атрідів править дощовою планетою Каладан. Проте юний син герцога Лето, Пол, бачить сни про пустельну планету Арракіс та її жителів — фременів. Невдовзі на Каладан прибуває посол імператора з наказом негайно переселитися на Арракіс задля встановлення там миру. Лето розуміє, що це якась пастка, проте кориться наказу, щоб не втратити честі свого Дому.',
        'https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg',
        156),
       (default,
        'Jujutsu Kaisen 0',
        '"2021-12-24"',
        'Yuta Okkotsu is a timid, 16-year-old, high-school student who is haunted by Rika Orimoto, the cursed spirit of his childhood friend who died six years ago because both of them promised to get married when they grow up. Whenever Yuta is bullied, Rika comes to his defense and violently assaults his attackers. In November 2016, Yuta meets Satoru Gojo, a jujutsu sorcerer under whose guidance he joins the Tokyo Prefectural Jujutsu High School to learn how to control Rika. There, Yuta meets the sorcerers Panda, Maki Zenin, and Toge Inumaki, who try exorcising Rika but are easily stopped by her. Yuta starts training with Maki, who mentors him in swordsmanship, which is combined with his energy to become like one of them. During a mission, Maki motivates Yuta to fight if he wants to be accepted, which causes him to briefly control Rika to destroy a cursed spirit.',
        'https://desu.shikimori.one/system/user_images/original/729338/1723023.jpg',
        1,
        105);

insert into movie_genre
values (7, 20),--jujutsu kaisen  - Фантастика
       (7, 10),--jujutsu kaisen - Аніме
       (3, 7),--Bohemian rhapsody - Драма
       (6, 2),--dune - Наукова фантастика
       (2, 8),-- green book - комедія
       (4, 2),-- interstellar - наукова фантастика
       (4, 18),-- interstellar - космос
       (5, 20),--tenet - фантастика
       (5, 6),--tenet - триллер
       (1, 2)--back in future
;

insert into movie_session
values (default, 1, 100, current_date, '09:00:00', '12:00:00'),
        (default, 2, 100, current_date, '12:00:00', '15:00:00'),
        (default, 3, 100, current_date, '15:00:00', '18:00:00'),
        (default, 5, 100, current_date, '18:00:00', '19:00:00'),
        (default, 4, 100, current_date, '21:00:00', '01:09:00'),

        (default, 3, 100, current_date + 1, '09:00:00', '12:00:00'),
        (default, 2, 100, current_date + 1, '12:00:00', '15:00:00'),
        (default, 2, 100, current_date + 1, '15:00:00', '18:00:00'),
        (default, 2, 100, current_date + 1, '18:00:00', '19:00:00'),
        (default, 4, 100, current_date + 1, '21:00:00', '01:09:00'),

        (default, 6, 100, current_date + 2, '09:00:00', '12:00:00'),
        (default, 7, 100, current_date + 2, '12:00:00', '15:00:00'),
        (default, 7, 100, current_date + 2, '15:00:00', '18:00:00'),
        (default, 6, 100, current_date + 2, '18:00:00', '19:00:00'),
        (default, 4, 100, current_date + 2, '21:00:00', '01:09:00'),

        (default, 7, 100, current_date + 3, '09:00:00', '12:00:00'),
        (default, 7, 100, current_date + 3, '12:00:00', '15:00:00'),
        (default, 7, 100, current_date + 3, '15:00:00', '18:00:00'),
        (default, 7, 100, current_date + 3, '18:00:00', '19:00:00'),
        (default, 7, 100, current_date + 3, '21:00:00', '01:09:00'),

        (default, 6, 100, current_date + 4, '09:00:00', '12:00:00'),
        (default, 6, 100, current_date + 4, '12:00:00', '15:00:00'),
        (default, 6, 100, current_date + 4, '15:00:00', '18:00:00'),
        (default, 6, 100, current_date + 4, '18:00:00', '19:00:00'),
        (default, 6, 100, current_date + 4, '21:00:00', '01:09:00'),

        (default, 5, 100, current_date + 5, '09:00:00', '12:00:00'),
        (default, 5, 100, current_date + 5, '12:00:00', '15:00:00'),
        (default, 5, 100, current_date + 5, '15:00:00', '18:00:00'),
        (default, 5, 100, current_date + 5, '18:00:00', '19:00:00'),
        (default, 5, 100, current_date + 5, '21:00:00', '01:09:00'),


        (default, 7, 100, current_date + 6, '09:00:00', '12:00:00'),
        (default, 7, 100, current_date + 6, '12:00:00', '15:00:00'),
        (default, 7, 100, current_date + 6, '15:00:00', '18:00:00'),
        (default, 7, 100, current_date + 6, '18:00:00', '19:00:00'),
        (default, 7, 100, current_date + 6, '21:00:00', '01:09:00')
;
-- insert into ticket
-- values ();

-- insert into ticket_order
-- values ();
