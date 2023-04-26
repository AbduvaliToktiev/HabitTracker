----tb_role-----------------------------------------------------------------------------------------------------------
insert into tb_role (status, name) VALUES ('ACTIVE', 'ROLE_ADMIN');
insert into tb_role (status, name) VALUES ('ACTIVE', 'ROLE_USER');

----tb_users----------------------------------------------------------------------------------------------------------
insert into tb_user(created,status,updated,age, email, gender, password, username)
VALUES ('2023-04-19 02:28:38.593', 'ACTIVE', '2023-04-19 02:28:38.593', 29, 'mirbek@gmail.com','MALE', '$2a$04$j8hS2sCsqpTzZBYPPRpBnubIuhee0Y1DmYOrwfTc9BO56Td7IiYW2', 'mirbek');

insert into tb_user(created,status,updated,age, email, gender, password, username)
VALUES ('2023-04-19 02:28:38.593', 'ACTIVE', '2023-04-19 02:28:38.593', 27, 'springboot1212@gmail.com','MALE', '$2a$10$DCj6yq2S9VHM5gS7p3ZRJ.fBCcjl7e4YHzYIodwwFic3IKR6EwpT2', 'ermek');

----tb_user_roles-----------------------------------------------------------------------------------------------------
insert into tb_user_roles(user_id,role_id)VALUES (1,1);
insert into tb_user_roles(user_id,role_id)VALUES (2,2);

----tb_profile--------------------------------------------------------------------------------------------------------
insert into tb_profile(color_theme, created, icon, language, name, status, updated, user_id)
VALUES ('Голубой_закат', '2023-04-23 21:27:56.04', 'https://www.flaticon.com/ru/free-icon/people_3722005', 'KG', 'Taalai', 'ACTIVE', '2023-04-23 21:27:56.04', 1);

insert into tb_profile(color_theme, created, icon, language, name, status, updated, user_id)
VALUES ('Голубой_закат', '2023-04-23 21:27:56.04', 'https://www.flaticon.com/ru/free-icon/people_3722005', 'KG', 'Ermek', 'ACTIVE', '2023-04-23 21:27:56.04', 2);

----tb_habit----------------------------------------------------------------------------------------------------------
insert into tb_habit(created, status, updated, description, end_date, frequency, name, start_date, target)
VALUES ('2023-04-21 00:00:00', 'ACTIVE', '2023-04-23', 'В течение 21 дня решать задания на leetcode', '2023-05-11', 'DAILY', '21 day coding in leetcode', '2023-04-21 00:00:00', 1);

----tb_progress----------------------------------------------------------------------------------------------------------
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-21', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-22', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-23', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-24', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-25', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-26', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-27', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-28', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-29', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-04-30', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-01', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-02', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-03', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-04', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-05', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-06', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-07', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-08', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-09', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-10', 1, 1, 2);
insert into tb_progress (created, status, updated, progress_date, target, habit_id, profile_id)
VALUES ('2023-04-23 23:28:11.88', 'ACTIVE', '2023-04-23 23:28:11.88', '2023-05-11', 1, 1, 2);