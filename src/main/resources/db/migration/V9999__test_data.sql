insert into user (id, username, password, role) values
    (1, 'admin', 'stalker', 'ROLE_ADMIN'),
    (2, 'cs_cons', 'stalker', 'ROLE_CONSULTANT'),
    (3, 'math_cons', 'stalker', 'ROLE_CONSULTANT'),
    (4, 'pr_cons', 'stalker', 'ROLE_CONSULTANT');

insert into subject (id, name) values
    (1, 'Computer Science'),
    (2, 'Math'),
    (3, 'Programming');

insert into sub_subject (id, name, parent_subject_id) values
    (1, 'Algorithms', 1),
    (2, 'Data Sctructuers', 1),
    (3, 'Topology', 2),
    (4, 'Sets', 2),
    (5, 'C++', 3),
    (6, 'Java', 3),
    (7, 'Haskell', 3);

insert into user_sub_subject (user_id, sub_subject_id) values
    (2, 1),
    (2, 2),
    (3, 3),
    (3, 4),
    (4, 5),
    (4, 6),
    (4, 7);