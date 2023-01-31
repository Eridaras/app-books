-- Luego insertamos los datos existentes de books en books_temp
INSERT INTO books_temp (author_id, isbn, title, price)
SELECT a.id, b.isbn, b.title, b.price
FROM books b
         JOIN authors a ON (split_part(b.author, ' ', 1) = a.first_name);
-- Eliminamos la tabla books
DROP TABLE books;

-- Renombramos books_temp a books
ALTER TABLE books_temp RENAME TO books;