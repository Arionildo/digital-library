INSERT INTO REVIEW (user_id, book_id, rating, comment, created_at)
SELECT
    user_id,
    book_id,
    FLOOR(RAND() * 10) + 1 AS rating,
    CASE WHEN FLOOR(RAND() * 4) = 1 THEN 'Good book' ELSE 'Nice book' END AS comment,
    DATEADD('DAY', -FLOOR(RAND() * 365), CURRENT_TIMESTAMP) AS created_at
FROM
    (SELECT u.id AS user_id, b.id AS book_id FROM users u CROSS JOIN Book b) AS cross_join;