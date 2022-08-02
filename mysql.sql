-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: assignment-data
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.21.10.2

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (15);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `p_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa7fbgew1hfv1w56wwut5l0eqk` (`p_fk`),
  CONSTRAINT `FKa7fbgew1hfv1w56wwut5l0eqk` FOREIGN KEY (`p_fk`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'https://m.media-amazon.com/images/I/615ekapl+pL._SL1500_.jpg',1),(2,'https://m.media-amazon.com/images/I/81kUhm40nQL._SL1500_.jpg',1),(3,'https://m.media-amazon.com/images/I/61jMxzQcPjL._SL1500_.jpg',1),(4,'https://m.media-amazon.com/images/I/71fkE5LBc+L._SL1500_.jpg',1),(5,'https://m.media-amazon.com/images/I/71fkE5LBc+L._SX569_.jpg',9),(6,'https://m.media-amazon.com/images/I/71fkE5LBc+L._SX569_.jpg',9),(7,'https://m.media-amazon.com/images/I/71fkE5LBc+L._SX569_.jpg',9),(8,'https://m.media-amazon.com/images/I/71fkE5LBc+L._SX569_.jpg',9);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments_details`
--

DROP TABLE IF EXISTS `payments_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments_details` (
  `id` bigint NOT NULL,
  `amount` bigint DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `customer_country` varchar(255) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `payment_id` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments_details`
--

LOCK TABLES `payments_details` WRITE;
/*!40000 ALTER TABLE `payments_details` DISABLE KEYS */;
INSERT INTO `payments_details` VALUES (12,29900,'inr','IN','prafullpatil0810@gmail.com','cus_MAhwbMxxxV1rEI','Prafull Patil','pi_3LSMWiSJIMUNrwun15pGsRcu','card','succeeded'),(13,299,'inr','IN','amanpatil@gmail.com','cus_MAiaFElzxThSTa','Aman Patil','pi_3LSN9DSJIMUNrwun0UNqVIQF','card','succeeded'),(14,299,'inr','IN','soma@gmail.com','cus_MAihblM605CJMq','Soma','pi_3LSNG2SJIMUNrwun1vV9xbzy','card','succeeded');
/*!40000 ALTER TABLE `payments_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` text,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'AMAN','2022-08-02 15:46:22','AMAN','2022-08-02 15:46:22','SKU D5515AI','<li>\n                Spatial audio with dynamic head tracking places sound all around\n                you\n              </li>\n              <li>Adaptive EQ automatically tunes music to your ears</li>\n              <li>All-new contoured design</li>\n              <li>\n                Force sensor lets you easily control your entertainment, answer\n                or end calls, and more\n              </li>\n              <li>Sweat and water resistant</li>\n              <li>Up to 6 hours of listening time with one charge</li>\n              <li>\n                Up to 30 hours of total listening time with the MagSafe Charging\n                Case\n              </li>\n              <li>Quick access to Siri by saying \"Hey Siri\"</li>\n <li>Effortless setup, in-ear detection and automatic switching for a magical experience\n</li>\n<li> Easily share audio between two sets of AirPods on your iPhone, iPad, iPod touch or Apple TV\n</li>','It’s magic, remastered!','New Apple Airpods (3rd Generation)',299,1),(9,'AMAN','2022-08-02 18:23:23','AMAN','2022-08-02 18:23:23','SKU D5515AI','hi','It’s magic, remastered!','New Apple Airpods (3rd Generation)',299,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-03  0:56:20
