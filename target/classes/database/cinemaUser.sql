create user cinema_user with encrypted password 'user';
grant all privileges on all tables in schema public TO cinema_user;
grant usage, select on all sequences in schema public TO cinema_user;