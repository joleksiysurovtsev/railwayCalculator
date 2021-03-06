-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: station_db
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `time_of_use`
--

DROP TABLE IF EXISTS `time_of_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_of_use` (
  `id` int NOT NULL,
  `TIME_COAST` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_of_use`
--

LOCK TABLES `time_of_use` WRITE;
/*!40000 ALTER TABLE `time_of_use` DISABLE KEYS */;
INSERT INTO `time_of_use` VALUES (1,0.80),(2,1.60),(3,2.40),(4,3.20),(5,4.50),(6,5.80),(7,7.10),(8,8.40),(9,9.70),(10,11.00),(11,12.30),(12,13.60),(13,14.90),(14,16.20),(15,17.50),(16,18.80),(17,20.10),(18,21.40),(19,22.70),(20,24.00),(21,25.30),(22,26.60),(23,27.90),(24,29.20),(25,32.70),(26,36.20),(27,39.70),(28,43.20),(29,46.70),(30,50.20),(31,53.70),(32,57.20),(33,60.70),(34,64.20),(35,67.70),(36,71.20),(37,82.70),(38,95.70),(39,110.20),(40,126.20),(41,142.20),(42,158.20),(43,174.20),(44,190.20),(45,206.20);
/*!40000 ALTER TABLE `time_of_use` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-01 16:38:03
