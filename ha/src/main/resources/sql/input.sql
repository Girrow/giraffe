CREATE TABLE `user` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`id` VARCHAR(20) NOT NULL,
	`pw` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`no`)
);

CREATE TABLE `post`(
  `no` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `postNo` INT,
  `writer` VARCHAR(20),
  `contspringent` VARCHAR(50),
  `delYn` CHAR(1) DEFAULT 'N'
);

INSERT INTO `user`(id,pw) values("harin","1234");
INSERT INTO `user`(id,pw) values("admin","1234");