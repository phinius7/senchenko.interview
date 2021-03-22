INSERT INTO `roles` (`title`)
    VALUE ('ROLE_ADMIN'), ('ROLE_PRESENTER'), ('ROLE_LISTENER');
GO

INSERT INTO `users` (`username`, `password`, `firstname`, `lastname`, `email`)
    VALUE ('theAdmin', '$2y$12$IvanjYcaZfLr0Y6BTJOo7ORvJgob6W405x.UPSbDsNjda3olW0XWK', 'Arthem', 'Senchenko',
           'phinius7@gmail.com'),
    ('thePresenter', '$2y$12$C7F6H9jk3L6zELQGvl8bwen6kRUk25Z5kgCggKjSVKosQCB8YlwcG', 'Ivan', 'Ivanov',
     'ivatov@example.com'),
    ('theListener', '$2y$12$PFrQ.QD1fPOF7B4ziu3MMuhp8kQB9we3iVmetdhO8qPkuTI90diC6', 'Peter', 'Petrov',
     'petrov@example.com');
GO

INSERT INTO `users_roles` (`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `username` = 'theAdmin'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `username` = 'theListener'),
       (SELECT id FROM `roles` WHERE `title` = 'ROLE_LISTENER')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `username` = 'thePresenter'),
       (SELECT id FROM `roles` WHERE `title` = 'ROLE_PRESENTER');
GO

INSERT INTO `rooms` (`classroom`)
    VALUE ('101'), ('102'), ('103');
GO

INSERT INTO `presentations` (`title`)
    VALUE ('The First Presentation'),
    ('How to use keyboard');
GO

INSERT INTO `schedules` (`start_time`, `end_time`, `presentation_id`, `room_id`)
    VALUE
    ('2021-03-18 17:00:00.000000', '2021-03-18 18:00:00.000000', 1, 1),
    ('2021-03-18 20:00:00.000000', '2021-03-18 21:00:00.000000', 2, 2),
    ('2021-03-17 20:00:00.000000', '2021-03-17 21:30:00.000000', 1, 3),
    ('2021-03-18 17:00:00.000000', '2021-03-18 18:00:00.000000', 2, 3);
GO

INSERT INTO `presentations_authors` (`presentation_id`, `author_id`)
VALUES (1, 1);
GO

INSERT INTO `presentations_authors` (`presentation_id`, `author_id`)
VALUES (2, 1);
GO

INSERT INTO `presentations_authors` (`presentation_id`, `author_id`)
VALUES (2, 2);
GO