create schema if not exists blog;

drop table if exists blog.post_topic;
drop sequence if exists blog.topic_seq;
drop table if exists blog.topic;
drop sequence if exists blog.post_seq;
drop table if exists blog.post;

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

create table blog.post (
    id bigint not null,
    created timestamp(6) not null,
    post text not null,
    is_public boolean default true,
    constraint pk_post_id primary key (id)
);

create table blog.post_topic (
  post_id bigint not null,
  topic_id bigint not null,
  constraint fk_post_topic_post_id foreign key (post_id) references blog.post(id),
  constraint fk_post_topic_topic_id foreign key (topic_id) references blog.topic(id)
);

create sequence blog.post_seq
    minvalue 1
    no maxvalue
    increment by 1
    start with 1
    nocache
    nocycle;
