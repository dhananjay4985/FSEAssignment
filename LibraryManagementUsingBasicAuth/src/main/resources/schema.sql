create table BOOK
(
   BOOKID long not null,
   BOOKNAME text,
   AUTHOR text,
   GENRE text,
   ISBN text,
   primary key(BOOKID)
);


create table USER(
   USERID long not null,
   EMAIL text,
   PASSWORD text,
   CATEGORY text,
   primary key(USERID)

);
