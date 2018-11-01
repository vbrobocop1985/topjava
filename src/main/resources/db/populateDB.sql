DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO MEALS(date_Time, description, calories, user_id) VALUES
(date '2015-05-30' + interval '10 hour', 'Завтрак', 500, 100000),
(date '2015-05-30' + interval '13 hour', 'Обед', 1000, 100000),
(date '2015-05-30' + interval '20 hour', 'Ужин', 500, 100000),
(date '2015-05-31' + interval '10 hour', 'Завтрак', 1000, 100000),
(date '2015-05-31' + interval '13 hour', 'Обед', 500, 100000),
(date '2015-05-31' + interval '20 hour', 'Ужин', 510, 100000),
(date '2015-06-01' + interval '14 hour', 'Обед', 500, 100001),
(date '2015-06-01' + interval '21 hour', 'Ужин', 1500, 100001);
