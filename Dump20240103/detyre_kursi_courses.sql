-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: detyre_kursi
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_name` varchar(50) NOT NULL,
  `description` text,
  `lecturer` varchar(100) DEFAULT NULL,
  `times` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `registered_students` int DEFAULT '0',
  PRIMARY KEY (`course_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('ArtHistory401','Renaissance Art','Dr. Johnson','Thursday 9:00AM - 10:30AM','Art Studio',0),('Astrophysics1001','Cosmology','Dr. Allen','Friday 2:00PM - 3:30PM','Observatory',0),('Biology101','Introduction to Biology','Prof. Taylor','Monday 3:00PM - 4:00PM','Lab C',0),('ChemicalEngineering701','Thermodynamics','Prof. Martinez','Tuesday 3:00PM - 4:00PM','Lab E',0),('ComputerGraphics901','3D Graphics Programming','Prof. Wang','Thursday 10:00AM - 11:00AM','Computer Lab',0),('Literature801','Modern Poetry','Dr. Lee','Wednesday 2:00PM - 3:00PM','Room 801',0),('Mathematics601','Advanced Calculus','Dr. White','Monday 10:00AM - 11:00AM','Room 601',0),('Physics501','Quantum Mechanics','Prof. Davis','Friday 1:00PM - 2:00PM','Lab D',0),('PoliticalScience301','International Relations','Prof. Rodriguez','Wednesday 1:00PM - 2:00PM','Room 302',0),('Psychology202','Cognitive Psychology','Dr. Allen','Tuesday 2:00PM - 3:30PM','Room 203',0);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-03 12:52:16
