# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: bookStore
# Generation Time: 2017-12-04 17:58:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Authors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Authors`;

CREATE TABLE `Authors` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `surname` char(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Authors` WRITE;
/*!40000 ALTER TABLE `Authors` DISABLE KEYS */;

INSERT INTO `Authors` (`author_id`, `name`, `surname`)
VALUES
	(1,'Paula','Hawkins'),
	(2,'Joanne','Rowling'),
	(3,'Scott','Fitzgerald'),
	(4,'Neil','Gaiman'),
	(5,'Terry','Pratchett');

/*!40000 ALTER TABLE `Authors` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Book_Author
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Book_Author`;

CREATE TABLE `Book_Author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Book_Author` WRITE;
/*!40000 ALTER TABLE `Book_Author` DISABLE KEYS */;

INSERT INTO `Book_Author` (`id`, `book_id`, `author_id`)
VALUES
	(1,1,1),
	(2,2,3),
	(3,3,2),
	(4,4,4),
	(5,4,5),
	(6,10,2);

/*!40000 ALTER TABLE `Book_Author` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Books`;

CREATE TABLE `Books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) NOT NULL,
  `user_id` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;

INSERT INTO `Books` (`id`, `name`, `user_id`)
VALUES
	(1,'The Girl on the Train','admin'),
	(2,'The Great Gatsby','admin'),
	(3,'Harry Potter and the Cursed Child, Parts 1 & 2','admin'),
	(4,'Good Omens','admin'),
	(10,'Harry Potter And The Sorcerer\'s Stone','admin');

/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `username` char(255) NOT NULL,
  `passHex` char(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `role` char(255) DEFAULT 'USER',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;

INSERT INTO `Users` (`username`, `passHex`, `enabled`, `role`)
VALUES
	('abc','90150983cd24fb0d6963f7d28e17f72',1,'USER'),
	('admin','21232f297a57a5a743894ae4a801fc3',1,'ADMIN'),
	('Test1','5a105e8b9d40e1329780d62ea2265d8a',1,'USER'),
	('Test2','ad23482925b933196ba818f7a872b',1,'USER'),
	('Test5','e3d74f3542b44a621ebed70dcefe13',1,'USER'),
	('user1','user1',1,'USER');

/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
