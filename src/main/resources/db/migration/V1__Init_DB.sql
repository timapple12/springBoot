 create table Message (
        id integer not null auto_increment,
        author varchar(255),
        filename varchar(255),
        message varchar(4096) not null,
        tag varchar(255),
        primary key (id))
        engine=MyISAM;

 create table users (
     id bigint not null auto_increment ,
     activation varchar(255),
     active bit not null,
     email varchar(128),
     password varchar(255) not null ,
     username varchar(255) not null ,
     primary key (id))
     engine=MyISAM;

 create table users_roles (
     user_id bigint not null,
     roles varchar(255))
     engine=MyISAM;

 alter table users_roles add constraint users_roles_users_fk foreign key (user_id) references users (id)