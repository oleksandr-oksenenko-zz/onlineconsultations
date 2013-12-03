insert into subject (subject_id, name, description) values (1, 'Programming languages', 'Programming languages are cool');
insert into subject (subject_id, name, description) values (2, 'Computer Science', 'Computer Science is cool');

insert into sub_subject (sub_subject_id, name, description, subject_id) values (1, 'C++', 'not bad', 1);
insert into sub_subject (sub_subject_id, name, description, subject_id) values (2, 'Java', 'also not bad', 1);
insert into sub_subject (sub_subject_id, name, description, subject_id) values (3, 'Haskell', 'awesome!', 1);

insert into role (role_id, role) values (1, 'ROLE_ADMIN');
insert into role (role_id, role) values (2, 'ROLE_CONSULTANT');

insert into user (user_id, username, password, firstname, lastname, role_id) values (1, 'admin', 'stalker', 'Alexander', 'Oksenenko', 1);
insert into user (user_id, username, password, firstname, lastname, role_id) values (2, 'crew4ok', 'stalker', 'Alexander', 'Oksenenko', 2);
insert into user (user_id, username, password, firstname, lastname, role_id) values (3, 'oks', 'stalker', 'Alexander', 'Oksenenko', 2);

insert into user_sub_subject (user_id, sub_subject_id) values (2, 1);
insert into user_sub_subject (user_id, sub_subject_id) values (2, 2);
insert into user_sub_subject (user_id, sub_subject_id) values (3, 3);