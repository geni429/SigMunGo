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
INSERT INTO `restaurant_img` VALUES ('2kknKoVXkS','TheTerrace (01).jpeg'),('2kknKoVXkS','TheTerrace (02).jpeg'),('2kknKoVXkS','TheTerrace (03).jpeg'),('2kknKoVXkS','TheTerrace (04).jpeg'),('2kknKoVXkS','TheTerrace (05).jpeg'),('2kknKoVXkS','TheTerrace (06).jpeg'),('2kknKoVXkS','TheTerrace (07).jpeg'),('2kknKoVXkS','TheTerrace (08).jpeg'),('2kknKoVXkS','TheTerrace (09).jpeg'),('2kknKoVXkS','TheTerrace (10).jpeg'),('2kknKoVXkS','TheTerrace (11).jpeg'),('2kknKoVXkS','TheTerrace (12).jpeg'),('2kknKoVXkS','TheTerrace (13).jpeg'),('2kknKoVXkS','TheTerrace (14).jpeg'),('2kknKoVXkS','TheTerrace (15).jpeg'),('2kknKoVXkS','TheTerrace (16).jpeg'),('2kknKoVXkS','TheTerrace (17).jpeg'),('2kknKoVXkS','TheTerrace (18).jpeg'),('2kknKoVXkS','TheTerrace (19).jpeg'),('2kknKoVXkS','TheTerrace (20).jpeg'),('2kknKoVXkS','TheTerrace (21).jpeg'),('gJJQqnGxDJ','mirak (1).jpeg'),('gJJQqnGxDJ','mirak (2).jpeg'),('gJJQqnGxDJ','mirak (3).jpeg'),('gJJQqnGxDJ','mirak (4).jpeg'),('qv6BncO1Wm','buldarg (1).jpeg'),('qv6BncO1Wm','buldarg (2).jpeg'),('qv6BncO1Wm','buldarg (3).jpeg'),('qv6BncO1Wm','buldarg (4).jpeg');
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

-- Dump completed on 2017-09-26 19:25:56
