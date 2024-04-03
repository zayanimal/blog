create schema if not exists blog;

drop table if exists blog.user_topic;
drop table if exists blog.post_topic;
drop sequence if exists blog.topic_seq;
drop table if exists blog.topic;
drop sequence if exists blog.post_seq;
drop table if exists blog.post;
drop table if exists blog.users;

create table blog.users (
    id uuid not null,
    name varchar(50) not null,
    pass varchar(100) not null,
    enabled boolean not null default true,
    created timestamp(6),
    constraint pk_user primary key (id)
);

create table blog.topic (
  id bigint not null,
  name varchar(255) not null,
  constraint pk_topic primary key (id)
);

create sequence blog.topic_seq
    minvalue 1
    no maxvalue
    increment by 1
    start with 1
    nocache
    nocycle;

create table blog.user_topic (
    user_id uuid not null,
    topic_id bigint not null,
    constraint fk_user_topic_user_id foreign key (user_id) references blog.users(id),
    constraint fk_user_topic_topic_id foreign key (topic_id) references blog.topic(id)
);

create table blog.post (
    id bigint not null,
    created timestamp(6) not null,
    post text not null,
    user_id uuid not null,
    topic_id bigint not null,
    is_public boolean default true,
    constraint pk_post_id primary key (id),
    constraint fk_post_user_id foreign key (user_id) references blog.users(id)
);

create sequence blog.post_seq
    minvalue 1
    no maxvalue
    increment by 1
    start with 1
    nocache
    nocycle;
