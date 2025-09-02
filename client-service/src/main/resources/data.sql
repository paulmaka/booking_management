CREATE TABLE IF NOT EXISTS clients
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    table_number     INTEGER             NOT NULL,
    book_date        TIMESTAMP           NOT NULL,
    register_date    TIMESTAMP           NOT NULL
    );



INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf244',
       'Vin Mistborn',
       'vin.mist@example.com',
       '92',
       '2025-08-25T09:00:00',
       '2025-08-24T12:30:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf244');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf245',
       'John Snow',
       'john.snow@example.com',
       '114',
       '2025-08-24T10:00:00',
       '2025-08-23T23:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf245');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf246',
       'Geralt Riviysky',
       'gera.riv@example.com',
       '90',
       '2025-08-23T16:40:00',
       '2025-08-22T22:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf246');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf247',
       'Wax Ladrian',
       'wax.ladrian@example.com',
       '48',
       '2025-08-26T20:00:00',
       '2025-08-26T18:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf247');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf250',
       'Maracy Colms',
       'mara.colms@example.com',
       '76',
       '2025-08-25T12:30:00',
       '2025-08-24T21:30:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf250');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf251',
       'Max Fray',
       'max.fray@example.com',
       '100',
       '2025-08-27T11:00:00',
       '2025-08-27T10:59:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf251');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf252',
       'Aragorn Elessar',
       'aragorn.elessar@example.com',
       '89',
       '2025-08-26T14:00:00',
       '2025-08-25T14:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf252');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf253',
       'Garret Shadow',
       'gar.shad@example.com',
       '17',
       '2025-08-27T22:00:00',
       '2025-08-26T23:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf253');


INSERT INTO clients (id, name, email, table_number, book_date, register_date)
SELECT '6290c469-d3dd-42e7-a95d-4eb09f1bf254',
       'Santyago Dark',
       'santya.dark@example.com',
       '200',
       '2025-08-28T12:00:00',
       '2025-08-27T12:00:00'
    WHERE NOT EXISTS (SELECT 1
                  FROM clients
                  WHERE id = '6290c469-d3dd-42e7-a95d-4eb09f1bf254');
