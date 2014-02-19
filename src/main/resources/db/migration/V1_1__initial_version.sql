create table user (
    id bigint primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(32) not null,
    firstname varchar(40),
    middlename varchar(40),
    lastname varchar(40),
    qualification varchar(40),
    role varchar(20) not null
);

create table subject (
    id bigint primary key auto_increment,
    name varchar(40) not null unique,
    description longtext
);

create table sub_subject (
    id bigint primary key auto_increment,
    name varchar(40) not null unique,
    description longtext,
    parent_subject_id bigint not null,
    foreign key (parent_subject_id) references subject(id)
);

create table user_sub_subject (
    user_id bigint not null auto_increment,
    sub_subject_id bigint not null,
    primary key (user_id, sub_subject_id),
    foreign key (user_id) references user(id),
    foreign key (sub_subject_id) references sub_subject(id)
);

create table chat (
    id bigint primary key auto_increment,
    chat_status varchar(40) not null,
    is_consultant_in_chat tinyint not null,
    is_anonym_in_chat tinyint not null,
    user_id bigint not null,
    session_id varchar(32) not null,
    foreign key (user_id) references user(id)
);

create table chat_message (
    id bigint primary key auto_increment,
    body longtext not null,
    timestamp datetime not null,
    chat_id bigint not null,
    user_id bigint,
    foreign key (chat_id) references chat(id),
    foreign key (user_id) references user(id)
);
