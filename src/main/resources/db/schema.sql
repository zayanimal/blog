create schema if not exists blog;

drop table if exists blog.post;

create table blog.post (
    id long not null,
    created timestamp(6) not null,
    post text not null,
    constraint pk_post_id primary key(id)
);
