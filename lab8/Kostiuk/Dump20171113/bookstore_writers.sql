CREATE DATABASE  IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookstore`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `writers`
--

DROP TABLE IF EXISTS `writers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `full_name_UNIQUE` (`full_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1164 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writers`
--

LOCK TABLES `writers` WRITE;
/*!40000 ALTER TABLE `writers` DISABLE KEYS */;
INSERT INTO `writers` VALUES (1105,'Albert Camus'),(1112,'Alfred Döblin'),(1128,'Anton Chekhov'),(1157,'Astrid Lindgren'),(1137,'Charles Dickens'),(1148,'Chinua Achebe'),(1146,'D. H. Lawrence'),(1102,'Dante Alighieri'),(1135,'Denis Diderot'),(1083,'Doris Lessing'),(1107,'Edgar Allan Poe'),(1081,'Elsa Morante'),(1154,'Emily Brontë'),(1092,'Ernest Hemingway'),(1106,'Euripides'),(1085,'Federico García Lorca'),(1145,'Fernando Pessoa'),(1124,'François Rabelais'),(1091,'Franz Kafka'),(1144,'Fyodor Dostoevsky'),(1100,'Gabriel García Márquez'),(1096,'Geoffrey Chaucer'),(1139,'George Eliot'),(1093,'George Orwell'),(1089,'Giacomo Leopardi'),(1114,'Giovanni Boccaccio'),(1153,'Günter Grass'),(1119,'Gustave Flaubert'),(1130,'Halldór Laxness'),(1111,'Hans Christian Andersen'),(1108,'Henrik Ibsen'),(1086,'Herman Melville'),(1160,'Homer'),(1099,'Honoré de Balzac'),(1103,'Italo Svevo'),(1088,'James Joyce'),(1104,'Jane Austen'),(1120,'João Guimarães Rosa'),(1143,'Johann Wolfgang von Goethe'),(1095,'Jonathan Swift'),(1150,'Jorge Luis Borges'),(1118,'José Saramago'),(1155,'Joseph Conrad'),(1109,'Juan Rulfo'),(1142,'Kālidāsa'),(1136,'Knut Hamsun'),(1131,'Laurence Sterne'),(1141,'Leo Tolstoy'),(1152,'Louis-Ferdinand Céline'),(1121,'Lu Xun'),(1079,'Marcel Proust'),(1122,'Marguerite Yourcenar'),(1090,'Mark Twain'),(1082,'Michel de Montaigne'),(1123,'Miguel de Cervantes'),(1125,'Murasaki Shikibu'),(1113,'Naguib Mahfouz'),(1140,'Nikolai Gogol'),(1098,'Nikos Kazantzakis'),(1138,'Njál\'s Saga_Unknown'),(1127,'One Thousand and One Nights_Unknown'),(1116,'Ovid'),(1159,'Paul Celan'),(1156,'Ralph Ellison'),(1115,'Robert Musil'),(1084,'Rumi'),(1117,'Saadi'),(1147,'Salman Rushdie'),(1133,'Samuel Beckett'),(1087,'Sophocles'),(1151,'Stendhal'),(1094,'Tayeb Salih'),(1097,'The Book Of Job_Unknown'),(1149,'The Epic Of Gilgamesh_Unknown'),(1110,'Thomas Mann'),(1126,'Toni Morrison'),(1162,'Unknown_Author'),(1158,'Valmiki'),(1132,'Virgil'),(1077,'Virginia Woolf'),(1129,'Vladimir Nabokov'),(1134,'Vyasa'),(1080,'Walt Whitman'),(1078,'William Faulkner'),(1161,'William Shakespeare'),(1101,'Yasunari Kawabata');
/*!40000 ALTER TABLE `writers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-13  7:54:16
