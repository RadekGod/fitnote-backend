-- liquibase formatted sql

-- changeset liquibase:diet_populate_1 runOnChange:false

INSERT INTO diet.meal
(id, name, kilocalories, proteins, carbohydrates, fat, salt, meal_date, user_id)
VALUES (1, 'Płatki kukurydziane', 326, 21.5, 79, 8, 2.4, '2023-10-06T12:03', 1);

INSERT INTO diet.meal
(id, name, kilocalories, proteins, carbohydrates, fat, salt, meal_date, user_id)
VALUES (2, 'Twaróg chudy', 250, 50, 12, 3, 2.4, '2023-10-06T18:03', 1);

INSERT INTO diet.meal
(id, name, kilocalories, proteins, carbohydrates, fat, salt, meal_date, user_id)
VALUES (3, 'Schabowy z zmieniakami', 800, 40, 90, 20, 5, '2023-10-05T12:03', 1);

INSERT INTO diet.meal
(id, name, kilocalories, proteins, carbohydrates, fat, salt, meal_date, user_id)
VALUES (4, 'Pizza', 1800, 40, 154, 30, 5, '2023-10-04T12:03', 1);
