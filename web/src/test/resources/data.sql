INSERT INTO tasks
(completed, id, description, name, priority)
VALUES(0, 1, 'Test', 'Test', 'MEDIUM');
INSERT INTO tasks
(completed, id, description, name, priority)
VALUES(0, 2, 'Test', 'Test', 'MEDIUM');
INSERT INTO tasks
(completed, id, description, name, priority)
VALUES(0, 3, 'Test', 'Test', 'MEDIUM');

alter sequence tasks_seq restart with 100;
