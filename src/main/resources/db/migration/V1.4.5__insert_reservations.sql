INSERT INTO RESERVATION (user_id, book_id, reserved_at, end_date)
SELECT
    user_id,
    book_id,
    DATEADD('DAY', -FLOOR(RAND() * 30), CURRENT_TIMESTAMP) AS reserved_at,
    DATEADD('DAY', FLOOR(RAND() * 30), CURRENT_TIMESTAMP) AS end_date
FROM
    (SELECT u.id AS user_id, b.id AS book_id FROM users u CROSS JOIN Book b) AS cross_join;