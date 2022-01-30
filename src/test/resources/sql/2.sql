-- alter table Person alter column dob drop on update;

insert into Person (first_name, last_name, permission, dob, email, password, address, telephone)
values  ('Jose', 'Eglesias', true, '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10', '+38007654321');
