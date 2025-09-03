CREATE TABLE IF NOT EXISTS clients
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    book_date        TIMESTAMP           NOT NULL,
    register_date    TIMESTAMP           NOT NULL
    );



INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf244',
       'Vin Mistborn',
       'vin.mist@example.com',
       '2025-08-25T09:00:00',
       '2025-08-24T12:30:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf244');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf245',
       'John Snow',
       'john.snow@example.com',
       '2025-08-24T10:00:00',
       '2025-08-23T23:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf245');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf246',
       'Geralt Riviysky',
       'gera.riv@example.com',
       '2025-08-23T16:40:00',
       '2025-08-22T22:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf246');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf247',
       'Wax Ladrian',
       'wax.ladrian@example.com',
       '2025-08-26T20:00:00',
       '2025-08-26T18:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf247');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf250',
       'Maracy Colms',
       'mara.colms@example.com',
       '2025-08-25T12:30:00',
       '2025-08-24T21:30:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf250');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf251',
       'Max Fray',
       'max.fray@example.com',
       '2025-08-27T11:00:00',
       '2025-08-27T10:59:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf251');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf252',
       'Aragorn Elessar',
       'aragorn.elessar@example.com',
       '2025-08-26T14:00:00',
       '2025-08-25T14:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf252');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf253',
       'Garret Shadow',
       'gar.shad@example.com',
       '2025-08-27T22:00:00',
       '2025-08-26T23:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf253');


INSERT INTO clients (id, name, email, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf254',
       'Santyago Dark',
       'santya.dark@example.com',
       '2025-08-28T12:00:00',
       '2025-08-27T12:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf254');



CREATE TABLE IF NOT EXISTS tables
(
    id              BIGINT          PRIMARY KEY,
    active          BOOLEAN         NOT NULL DEFAULT FALSE,
    client_id       UUID            UNIQUE,
    count_of_usage  INTEGER         NOT NULL DEFAULT 0,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);


INSERT INTO tables (id)
SELECT '1'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '1');

INSERT INTO tables (id)
SELECT '2'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '2');

INSERT INTO tables (id)
SELECT '3'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '4');

INSERT INTO tables (id)
SELECT '5'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '5');

INSERT INTO tables (id)
SELECT '6'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '6');

INSERT INTO tables (id)
SELECT '7'
WHERE NOT EXISTS (SELECT 1
    FROM tables
    WHERE id = '7');



CREATE TABLE IF NOT EXISTS dishes
(
    id              UUID                PRIMARY KEY,
    cost            INTEGER             NOT NULL,
    category        VARCHAR(255)        NOT NULL,
    name            VARCHAR(255)        NOT NULL,
    description     VARCHAR(800)        NOT NULL
);

INSERT INTO dishes (id, cost, category, name, description)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf261',
       '1000',
       'Десерт',
       'Мороженое',
       'Вкусное холодное мороженое'
    WHERE NOT EXISTS (SELECT 1
                  FROM dishes
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf261');

INSERT INTO dishes (id, cost, category, name, description)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf262',
       '800',
       'Основное блюдо',
       'Суп',
       'Вкусный суп'
    WHERE NOT EXISTS (SELECT 1
                  FROM dishes
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf262');

INSERT INTO dishes (id, cost, category, name, description)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf263',
       '500',
       'Напиток',
       'Чай',
       'Чёрный чай'
    WHERE NOT EXISTS (SELECT 1
                  FROM dishes
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf263');

INSERT INTO dishes (id, cost, category, name, description)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf264',
       '700',
       'Второе блюдо',
       'Плов',
       'Вкусный плов'
    WHERE NOT EXISTS (SELECT 1
                  FROM dishes
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf264');



CREATE TABLE IF NOT EXISTS orders
(
    id              UUID                PRIMARY KEY,
    client_id       UUID                NOT NULL,
    dish_id         UUID                NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
);

INSERT INTO orders (id, client_id, dish_id)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf271',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf254',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf261'
    WHERE NOT EXISTS (SELECT 1
                  FROM orders
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf271');

INSERT INTO orders (id, client_id, dish_id)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf272',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf254',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf262'
    WHERE NOT EXISTS (SELECT 1
                  FROM orders
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf272');

INSERT INTO orders (id, client_id, dish_id)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf273',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf254',
       '6290c469-d3dd-42e7-a95d-4eb09f1bf263'
    WHERE NOT EXISTS (SELECT 1
                  FROM orders
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf273');