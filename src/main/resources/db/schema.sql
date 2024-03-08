create schema if not exists blog;

drop sequence if exists blog.post_seq;
drop table if exists blog.post;

create table blog.post (
    id long not null,
    created timestamp(6) not null,
    post text not null,
    is_public boolean default true,
    constraint pk_post_id primary key(id)
);

create sequence blog.post_seq
    minvalue 1
    no maxvalue
    increment by 1
    start with 1
    nocache
    nocycle;
