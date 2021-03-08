create table users(id varchar2(12) primary key, passwd varchar2(12), addr varchar2(50), age number, email varchar2(30), gender char(1), name varchar2(12), nation varchar2(16));
insert into users values('java','java','서울시',40,'aaa@aaa.com','M','오정원','대한민국');


alter table users add (grade varchar2(1));

update users set grade = 'N' where id='java';

insert into users values ('admin','admin','대구시','40','sss@sss.com','M','관리자','대한민국','S');

select * from users;