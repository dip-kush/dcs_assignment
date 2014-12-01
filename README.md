Make a database mydb 

and a table phonebook

change the username and password in file HelloServer.java

--------------------------------------
Creating a database
--------------------------------------

create database mybook;



--------------------------------------
Creating a table
--------------------------------------

create table phonebook (
firstname varchar(30),
lastname varchar(20),
phone varchar(10),
email varchar(20),
city varchar(20),
state char(2)
);


-----------------------------------------------
Instructions for running java corba application
-----------------------------------------------

compile the java program using :- javac *.java

orbd -ORBInitialPort 1050&

java HelloServer -ORBInitialPort 1050 -ORBInitialHost localhost&


java HelloClient -ORBInitialPort 1050 -ORBInitialHost localhost



----------------------------------------------
Instructions for running java rmi application
---------------------------------------------

compile using javac *.java

run server:- java HelloWorldServer

run client:- java HelloWorldClient





