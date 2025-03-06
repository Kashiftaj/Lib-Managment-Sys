-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lab_managment
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'kashif taj','kalhoro','pakistan'),(5,'Azan hameed','Soomro','pakistan'),(10,'farhan ','massan','pakistan'),(11,'Ahmar ','Mangi','Sindhii'),(12,'hasnain','channo','pakistani'),(16,'soban','mahesar','pakistan');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `ISBN` int NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(30) NOT NULL,
  `PUBLISH_YEAR` varchar(50) DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `EDDITION` varchar(10) DEFAULT NULL,
  `AUTHOR_ID` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `books_ibfk_2` (`category_id`),
  KEY `books_ibfk_1` (`AUTHOR_ID`),
  KEY `fk_books_publisher` (`pid`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `author` (`id`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_books_publisher` FOREIGN KEY (`pid`) REFERENCES `publisher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (4,'abc','2021','Available','1',1,5,2),(5,'xyzz','2023','Not available','2',5,6,2),(6,'sa','231','Available','3',5,9,2),(7,'Rumi','2003','Available','2',11,5,3),(9,'java white','2003','Not available','8th',11,6,3),(10,'40 rules of love','2003','Available','1',1,5,2),(11,'art of being alone','2022','Available','2nd',11,1,3),(14,'herry poter','2004','Available','2nd',5,10,2),(15,'Atomic Habbit','2021','Available','5',5,1,2),(16,'LOVE ','2002','Available','01',1,5,2),(17,'deep work','2016','Available','1',12,1,3);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fiction','Active'),(5,'LOVE','Active'),(6,'science fiction','Active'),(9,'poetry','Deactive'),(10,'drama','Deactive'),(13,'none fiction','Active'),(14,'poetry','Active');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fine`
--

DROP TABLE IF EXISTS `fine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fine` (
  `member_id` int DEFAULT NULL,
  `amount` int NOT NULL,
  `reason` varchar(50) NOT NULL,
  `status` varchar(15) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fine_ibfk_1` (`member_id`),
  CONSTRAINT `fine_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fine`
--

LOCK TABLES `fine` WRITE;
/*!40000 ALTER TABLE `fine` DISABLE KEYS */;
INSERT INTO `fine` VALUES (4,150,'late return','unpaid',1),(2,100,'late return','unpaid',5),(5,150,'late return','clear',8),(6,50,'late return','clear',9),(5,0,'returned on time','clear',10),(6,0,'returned on time','clear',13),(5,300,'late return','clear',14),(5,100,'late return','clear',15),(2,200,'late return','unpaid',16),(2,1400,'late return','unpaid',17),(4,0,'returned on time','clear',18),(2,0,'returned on time','clear',19);
/*!40000 ALTER TABLE `fine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lendbook`
--

DROP TABLE IF EXISTS `lendbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lendbook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `issue_date` date NOT NULL,
  `return_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `borrow_ibfk_2` (`member_id`),
  KEY `lendbook_ibfk_1` (`book_id`),
  CONSTRAINT `lendbook_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lendbook_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lendbook`
--

LOCK TABLES `lendbook` WRITE;
/*!40000 ALTER TABLE `lendbook` DISABLE KEYS */;
INSERT INTO `lendbook` VALUES (19,9,4,'2024-04-29','2024-05-08'),(21,10,5,'2024-04-29','2024-05-09'),(22,11,6,'2024-04-29','2024-05-09'),(27,10,9,'2024-04-23','2024-04-27'),(28,14,10,'2024-04-23','2024-04-28'),(29,15,11,'2024-04-23','2024-04-29'),(30,5,12,'2024-04-23','2024-04-24'),(31,11,4,'2024-04-30','2024-05-10');
/*!40000 ALTER TABLE `lendbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logindetails`
--

DROP TABLE IF EXISTS `logindetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logindetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logindetails`
--

LOCK TABLES `logindetails` WRITE;
/*!40000 ALTER TABLE `logindetails` DISABLE KEYS */;
INSERT INTO `logindetails` VALUES (1,'kashif@admin','syed'),(2,'farhan@admin','admin2'),(3,'azan@admin','admin3');
/*!40000 ALTER TABLE `logindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,'kashif','kalhoro','0340','Active'),(4,'yasir','mahar','098765','Inactive'),(5,'farhan','massan','0987654','Active'),(6,'kaleem','malikkk','03445','Inactive'),(8,'Azan','Hameed','0123456','Inactive'),(9,'usama','mangi','0340-2233456','Active'),(10,'shakeel','mallah','03101122334','Active'),(11,'umer','syed','0987654','Active'),(12,'Gulam Qadir','Junejo','098765987','Active'),(13,'soban','shah','98765','Active');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `contact` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (2,'Farhan','massan','034'),(3,'shahbaz','mahar','0322222');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnedbooks`
--

DROP TABLE IF EXISTS `returnedbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returnedbooks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `fine_id` int DEFAULT NULL,
  `member_name` varchar(20) DEFAULT NULL,
  `book_name` varchar(50) DEFAULT NULL,
  `daysElap` int DEFAULT NULL,
  `returned_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fine_id` (`fine_id`),
  CONSTRAINT `returnedbooks_ibfk_1` FOREIGN KEY (`fine_id`) REFERENCES `fine` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnedbooks`
--

LOCK TABLES `returnedbooks` WRITE;
/*!40000 ALTER TABLE `returnedbooks` DISABLE KEYS */;
INSERT INTO `returnedbooks` VALUES (5,4,1,'yasir','xyzz',3,'2024-04-24'),(7,2,5,'kashif','Rumi',2,'2024-04-24'),(9,5,8,'farhan','Rumi',3,'2024-04-24'),(12,6,9,'kaleem','Rumi',1,'2024-04-24'),(16,6,13,'kaleem','k',0,'2024-04-24'),(17,5,14,'farhan','Rumi',6,'2024-04-24'),(18,5,15,'farhan','sa',2,'2024-04-28'),(19,2,16,'kashif','abc',4,'2024-04-28'),(20,2,17,'kashif','art of being alone',28,'2024-04-30'),(21,4,18,'yasir','herry poter',0,'2024-04-30'),(22,2,19,'kashif','xyzz',0,'2024-04-30');
/*!40000 ALTER TABLE `returnedbooks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 11:40:05
