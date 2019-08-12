CREATE TABLE `user` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`id` VARCHAR(20) NOT NULL,
	`pw` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDBspringbootspringboot
;

CREATE TABLE `post`(
  `no` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `postNo` INT,
  `writer` VARCHAR(20spring),
  `content` VARCHAR(50),
  `delYn` CHAR(1) DEFAULT 'N'
)