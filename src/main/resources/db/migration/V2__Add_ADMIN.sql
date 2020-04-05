insert into users(id, username, password, active) values (1,'admin','password',true);
insert into users_roles(user_id,roles) values (1,'USER'),(1,'ADMIN');