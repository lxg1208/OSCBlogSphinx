CREATE TABLE `article` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`title` varchar(255) DEFAULT NULL,
	`content` longblob DEFAULT NULL,
	`update_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
);