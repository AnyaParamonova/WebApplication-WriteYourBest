USE Write_Your_Best_Database;

SET FOREIGN_KEY_CHECKS=0
;

DROP TABLE IF EXISTS `ACCOUNTS` CASCADE
;

DROP TABLE IF EXISTS `BANLIST` CASCADE
;

DROP TABLE IF EXISTS `COMPOSITIONS` CASCADE
;

DROP TABLE IF EXISTS `PAYMENTS` CASCADE
;

DROP TABLE IF EXISTS `RELATIONSHIPS` CASCADE
;

DROP TABLE IF EXISTS `THEMES` CASCADE
;

CREATE TABLE `ACCOUNTS`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`TYPE` CHAR(10) NOT NULL,
	`STATUS` CHAR(10) NOT NULL,
	`CREATION_DATE` DATETIME NOT NULL,
	`NICKNAME` CHAR(30) NOT NULL UNIQUE,
	`PASSWORD` CHAR(30) NOT NULL,
	`EMAIL` CHAR(30) NOT NULL,
	CONSTRAINT `PK_ACCOUNTS` PRIMARY KEY (`ID` ASC)
)
;

CREATE TABLE `BANLIST`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`USER_ID` INTEGER NOT NULL,
	`ADMIN_ID` INTEGER NOT NULL,
	`BAN_DATE` DATETIME NOT NULL,
	`UNBAN_DATE` DATETIME NOT NULL,
	CONSTRAINT `PK_BANLIST` PRIMARY KEY (`ID` ASC)
)
;

CREATE TABLE `COMPOSITIONS`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`THEME_ID` INTEGER NOT NULL,
	`AUTHOR_ID` INTEGER NOT NULL,
	`CREATION_DATE` DATETIME NOT NULL,
	`CAPTION` TEXT NOT NULL,
	`BODY` TEXT NOT NULL,
	CONSTRAINT `PK_COMPOSITIONS` PRIMARY KEY (`ID` ASC)
)
;

CREATE TABLE `PAYMENTS`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`AMOUNT` INTEGER NOT NULL,
	`DATE` DATETIME NOT NULL,
	`USER_ID` INTEGER NOT NULL,
	CONSTRAINT `PK_PAYMENTS` PRIMARY KEY (`ID` ASC)
)
;

CREATE TABLE `RELATIONSHIPS`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`USER_ID` INTEGER NOT NULL,
	`FOLLOWING_ID` INTEGER NOT NULL,
	CONSTRAINT `PK_RELATIONSHIPS` PRIMARY KEY (`ID` ASC)
)
;

CREATE TABLE `THEMES`
(
	`ID` INTEGER NOT NULL AUTO_INCREMENT,
	`ADMIN_ID` INTEGER NOT NULL,
	`CREATION_DATE` DATETIME NOT NULL,
	`BODY` TEXT NOT NULL,
	CONSTRAINT `PK_THEMES` PRIMARY KEY (`ID` ASC)
)
;

ALTER TABLE `BANLIST` 
 ADD INDEX `IXFK_BANLIST_ACCOUNTS` (`USER_ID` ASC)
;

ALTER TABLE `BANLIST` 
 ADD INDEX `IXFK_BANLIST_ACCOUNTS_02` (`ADMIN_ID` ASC)
;

ALTER TABLE `COMPOSITIONS` 
 ADD INDEX `IXFK_COMPOSITIONS_ACCOUNTS` (`AUTHOR_ID` ASC)
;

ALTER TABLE `COMPOSITIONS` 
 ADD INDEX `IXFK_COMPOSITIONS_THEMES` (`THEME_ID` ASC)
;

ALTER TABLE `PAYMENTS` 
 ADD INDEX `IXFK_PAYMENTS_ACCOUNTS` (`USER_ID` ASC)
;

ALTER TABLE `RELATIONSHIPS` 
 ADD INDEX `IXFK_RELATIONSHIPS_ACCOUNTS` (`USER_ID` ASC)
;

ALTER TABLE `RELATIONSHIPS` 
 ADD INDEX `IXFK_RELATIONSHIPS_ACCOUNTS_02` (`FOLLOWING_ID` ASC)
;

ALTER TABLE `THEMES` 
 ADD INDEX `IXFK_THEMES_ACCOUNTS` (`ADMIN_ID` ASC)
;

ALTER TABLE `BANLIST` 
 ADD CONSTRAINT `FK_BANLIST_ACCOUNTS`
	FOREIGN KEY (`USER_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `BANLIST` 
 ADD CONSTRAINT `FK_BANLIST_ACCOUNTS_02`
	FOREIGN KEY (`ADMIN_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `COMPOSITIONS` 
 ADD CONSTRAINT `FK_COMPOSITIONS_ACCOUNTS`
	FOREIGN KEY (`AUTHOR_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `COMPOSITIONS` 
 ADD CONSTRAINT `FK_COMPOSITIONS_THEMES`
	FOREIGN KEY (`THEME_ID`) REFERENCES `THEMES` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `PAYMENTS` 
 ADD CONSTRAINT `FK_PAYMENTS_ACCOUNTS`
	FOREIGN KEY (`USER_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `RELATIONSHIPS` 
 ADD CONSTRAINT `FK_RELATIONSHIPS_ACCOUNTS`
	FOREIGN KEY (`USER_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `RELATIONSHIPS` 
 ADD CONSTRAINT `FK_RELATIONSHIPS_ACCOUNTS_02`
	FOREIGN KEY (`FOLLOWING_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `THEMES` 
 ADD CONSTRAINT `FK_THEMES_ACCOUNTS`
	FOREIGN KEY (`ADMIN_ID`) REFERENCES `ACCOUNTS` (`ID`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1
;

