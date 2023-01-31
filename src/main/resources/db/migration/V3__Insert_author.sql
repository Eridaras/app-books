INSERT INTO authors (first_name, last_name)
SELECT DISTINCT first_name, last_name
FROM (
         SELECT
             split_part(author, ' ', 1) AS first_name,
             split_part(author, ' ', 2) AS last_name
         FROM books
     )authors_temp;