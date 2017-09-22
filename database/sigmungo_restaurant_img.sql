-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sigmungo
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `restaurant_img`
--

DROP TABLE IF EXISTS `restaurant_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_img` (
  `contentid` varchar(10) NOT NULL,
  `img` varchar(45) NOT NULL,
  PRIMARY KEY (`contentid`,`img`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_img`
--

LOCK TABLES `restaurant_img` WRITE;
/*!40000 ALTER TABLE `restaurant_img` DISABLE KEYS */;
INSERT INTO `restaurant_img` VALUES ('2kknKoVXkS','images/TheTerrace(1)'),('2kknKoVXkS','images/TheTerrace(10)'),('2kknKoVXkS','images/TheTerrace(11)'),('2kknKoVXkS','images/TheTerrace(12)'),('2kknKoVXkS','images/TheTerrace(13)'),('2kknKoVXkS','images/TheTerrace(14)'),('2kknKoVXkS','images/TheTerrace(15)'),('2kknKoVXkS','images/TheTerrace(16)'),('2kknKoVXkS','images/TheTerrace(17)'),('2kknKoVXkS','images/TheTerrace(18)'),('2kknKoVXkS','images/TheTerrace(19)'),('2kknKoVXkS','images/TheTerrace(2)'),('2kknKoVXkS','images/TheTerrace(20)'),('2kknKoVXkS','images/TheTerrace(21)'),('2kknKoVXkS','images/TheTerrace(3)'),('2kknKoVXkS','images/TheTerrace(4)'),('2kknKoVXkS','images/TheTerrace(5)'),('2kknKoVXkS','images/TheTerrace(6)'),('2kknKoVXkS','images/TheTerrace(7)'),('2kknKoVXkS','images/TheTerrace(8)'),('2kknKoVXkS','images/TheTerrace(9)');
/*!40000 ALTER TABLE `restaurant_img` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-23  1:37:56
