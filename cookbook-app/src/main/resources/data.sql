INSERT INTO "PUBLIC"."COMMENT" VALUES
(1, 'Good', TIMESTAMP '2021-05-15 15:42:47.205971');

INSERT INTO "PUBLIC"."COOK" VALUES
(1, '$2a$12$.43oar82pogu47bYwU1sGO0.x1X7/cSPw6QD/Tnrgzgg/ahtxtkOS', 'USER', 'gipszjakab'),
(2, '$2a$12$H6YdffI0mfppzS6kc4v94OnZ7RDS7xGX9K2ALTcZ3DpFQbswzjFey', 'USER', 'palkata');

INSERT INTO "PUBLIC"."COOK_COMMENTS" VALUES
(2, 1);

INSERT INTO "PUBLIC"."INGREDIENT" VALUES
(1, 15.0, STRINGDECODE('olive\r'), 'MILLILITER'),
(2, 1.0, STRINGDECODE('garlic\r'), 'PIECE'),
(3, 0.5, STRINGDECODE('onion\r'), 'PIECE'),
(4, 200.0, STRINGDECODE('peas\r'), 'GRAM'),
(5, 300.0, STRINGDECODE('chicken\r'), 'MILLILITER'),
(6, 50.0, 'cream', 'MILLILITER');

INSERT INTO "PUBLIC"."RECIPE" VALUES
(1, 'Pea soup', STRINGDECODE('Heat the oil in a saucepan over a medium heat. Add the onion and garlic and fry for 34 minutes, until softened.\r\nAdd the frozen peas and chicken or vegetable stock and bring to the boil. Reduce the heat and simmer for ten minutes.\r\nAdd the cream and use a hand blender to liquidise the soup.\r\nSeason, to taste and serve in a warm bowl, garnished with a mint leaf.'), 2, 1);               

INSERT INTO "PUBLIC"."RECIPE_CATEGORIES" VALUES
(1, 'SOUP'),
(1, 'SALTY');

INSERT INTO "PUBLIC"."RECIPE_COMMENTS" VALUES
(1, 1);

INSERT INTO "PUBLIC"."RECIPE_INGREDIENTS" VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6);