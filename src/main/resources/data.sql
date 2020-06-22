
DROP TABLE IF EXISTS `history`;
DROP TABLE IF EXISTS `account`;


create table account (id integer not null auto_increment, balance bigint, date_created datetime, first_name varchar(255), last_name varchar(255), last_updated datetime, primary key (id)) ;
create table history (id integer not null auto_increment, amount bigint, date_created datetime, new_balance bigint, old_balance bigint, operation_type integer, account_id integer not null, primary key (id));
alter table history add constraint FK2mpn4nxqqsu7euii4hwhbjeg8 foreign key (account_id) references account (id);




INSERT INTO `account` VALUES (1,3000,NULL,'user1','user1','2020-06-19 09:36:05'),(2,2000,NULL,'user2','user2',NULL);

INSERT INTO `history` VALUES (1,2000,'2020-06-18 16:49:29',4000,2000,0,1),(2,2000,'2020-06-18 16:52:14',4000,2000,0,1),(3,3000,'2020-06-18 16:53:48',7000,4000,0,1),(4,3000,'2020-06-18 16:54:36',4000,7000,0,1),(5,3000,'2020-06-19 09:35:30',7000,4000,0,1),(6,7000,'2020-06-19 09:35:41',0,7000,0,1),(7,3000,'2020-06-19 09:36:05',3000,0,0,1);


