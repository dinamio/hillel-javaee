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
-- Table structure for table `book_rev`
--

DROP TABLE IF EXISTS `book_rev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_rev` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `rev_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_id_key_idx` (`book_id`),
  KEY `reviewer_id_key_idx` (`rev_id`),
  CONSTRAINT `book_id_key` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reviewer_id_key` FOREIGN KEY (`rev_id`) REFERENCES `writers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=719 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_rev`
--

LOCK TABLES `book_rev` WRITE;
/*!40000 ALTER TABLE `book_rev` DISABLE KEYS */;
INSERT INTO `book_rev` VALUES (562,1427,1125),(563,1428,1153),(564,1429,1123),(565,1429,1102),(566,1429,1105),(567,1430,1125),(568,1432,1156),(569,1432,1155),(570,1434,1087),(571,1435,1121),(572,1436,1105),(573,1436,1099),(574,1436,1152),(575,1437,1154),(576,1437,1138),(577,1437,1084),(578,1438,1080),(579,1438,1094),(580,1439,1097),(581,1440,1110),(582,1440,1117),(583,1441,1104),(584,1441,1120),(585,1441,1117),(586,1442,1129),(587,1442,1087),(588,1443,1086),(589,1443,1099),(590,1443,1082),(591,1444,1088),(592,1444,1123),(593,1445,1118),(594,1446,1107),(595,1446,1080),(596,1446,1084),(597,1449,1123),(598,1449,1136),(599,1449,1084),(600,1450,1144),(601,1450,1113),(602,1451,1121),(603,1452,1078),(604,1452,1153),(605,1452,1150),(606,1453,1134),(607,1453,1079),(608,1455,1128),(609,1455,1093),(610,1455,1159),(611,1456,1115),(612,1456,1147),(613,1456,1108),(614,1457,1098),(615,1457,1151),(616,1457,1116),(617,1459,1117),(618,1459,1121),(619,1460,1086),(620,1461,1092),(621,1461,1114),(622,1462,1149),(623,1462,1145),(624,1462,1095),(625,1463,1152),(626,1463,1115),(627,1463,1087),(628,1464,1138),(629,1464,1135),(630,1464,1102),(631,1465,1083),(632,1465,1096),(633,1466,1109),(634,1466,1141),(635,1466,1079),(636,1467,1140),(637,1467,1093),(638,1468,1160),(639,1468,1156),(640,1468,1108),(641,1470,1133),(642,1470,1150),(643,1471,1125),(644,1471,1141),(645,1471,1084),(646,1472,1085),(647,1472,1082),(648,1473,1121),(649,1474,1138),(650,1474,1157),(651,1474,1134),(652,1475,1106),(653,1475,1139),(654,1475,1102),(655,1476,1111),(656,1479,1140),(657,1479,1089),(658,1479,1097),(659,1481,1104),(660,1482,1148),(661,1482,1143),(662,1484,1146),(663,1484,1107),(664,1485,1147),(665,1485,1102),(666,1485,1084),(667,1486,1098),(668,1486,1131),(669,1486,1151),(670,1487,1111),(671,1487,1103),(672,1489,1140),(673,1490,1116),(674,1490,1114),(675,1490,1084),(676,1492,1139),(677,1492,1107),(678,1492,1084),(679,1493,1157),(680,1495,1158),(681,1495,1142),(682,1496,1114),(683,1496,1154),(684,1497,1145),(685,1497,1098),(686,1501,1141),(687,1501,1093),(688,1502,1110),(689,1502,1142),(690,1502,1087),(691,1503,1128),(692,1509,1087),(693,1509,1119),(694,1510,1094),(695,1511,1145),(696,1512,1143),(697,1512,1117),(698,1513,1100),(699,1514,1096),(700,1515,1101),(701,1515,1120),(702,1515,1125),(703,1517,1129),(704,1517,1138),(705,1517,1077),(706,1518,1093),(707,1518,1083),(708,1519,1149),(709,1519,1138),(710,1520,1135),(711,1522,1139),(712,1522,1078),(713,1522,1119),(714,1523,1129),(715,1524,1126),(716,1524,1122),(717,1525,1079),(718,1526,1105);
/*!40000 ALTER TABLE `book_rev` ENABLE KEYS */;
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
