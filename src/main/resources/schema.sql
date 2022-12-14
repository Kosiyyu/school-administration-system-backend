

drop table student;
drop sequence student_seq;

create sequence student_seq;

create table student(
  id BIGINT not null default nextval('student_seq') primary key,
  firstname varchar(255),
  lastname varchar(255),
  birthday_date date,
  address varchar(255),
  email varchar(255),
  telephone_number varchar(255)
)

