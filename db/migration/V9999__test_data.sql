insert into user (id, username, password, role, lastname, firstname) values
    (1, 'admin', 'e42ddd748c23785103b4e0f99a0e33954a36bf871a6710f6392a49b77992a00a', 'ROLE_ADMIN', 'Oksenenko', 'Aleksandr'),
    (2, 'cs_cons', 'e42ddd748c23785103b4e0f99a0e33954a36bf871a6710f6392a49b77992a00a', 'ROLE_CONSULTANT', 'Karpenko', 'Anna'),
    (3, 'math_cons', 'e42ddd748c23785103b4e0f99a0e33954a36bf871a6710f6392a49b77992a00a', 'ROLE_CONSULTANT', 'Gavrilevskiy', 'Vladislav'),
    (4, 'pr_cons', 'e42ddd748c23785103b4e0f99a0e33954a36bf871a6710f6392a49b77992a00a', 'ROLE_CONSULTANT', 'Yavtushenko', 'Vasiliy');

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