-- Primero creamos la tabla authors
CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         first_name TEXT NOT NULL,
                         last_name TEXT
);

-- Después creamos una tabla temporal books_temp
CREATE TABLE books_temp (
                            id SERIAL PRIMARY KEY,
                            author_id INTEGER NOT NULL references authors (id),
                            isbn TEXT NOT NULL,
                            title TEXT NOT NULL,
                            price decimal(8,2)
);

