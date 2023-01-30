BEGIN;
CREATE TEMPORARY TABLE books_temp AS (
    SELECT id, author, isbn, title, price
    FROM books
);

DROP TABLE books;

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       author_id INTEGER NOT NULL REFERENCES authors (id),
                       isbn TEXT NOT NULL,
                       title TEXT NOT NULL,
                       price NUMERIC NOT NULL
);

INSERT INTO authors (first_name)
SELECT DISTINCT author FROM books_temp;

UPDATE books_temp SET author = authors.id
FROM authors
WHERE books_temp.author = authors.first_name;

INSERT INTO books (id, author_id, isbn, title, price)
SELECT id, author, isbn, title, price
FROM books_temp;

DROP TABLE books_temp;
COMMIT;