/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  D
 * Created: Nov 10, 2018
 */

CREATE DATABASE  IF NOT EXISTS `roland_webshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `roland_webshop`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: roland_webshop
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.30-MariaDB

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Guitar'),(2,'Bass guitar'),(3,'Amp'),(4,'Pedal');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `category` int(11) NOT NULL,
  `manufacturer` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(500) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `cathegory_fk_idx` (`category`),
  KEY `manufacturer_fk_idx` (`manufacturer`),
  CONSTRAINT `cathegory_fk` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `manufacturer_fk` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`b11`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Fender Stratocaster',1,1,800,'Body: Maple | Neck: Rosewood | Pickups: EMG','images/Fender Stratocaster.jpg'),(2,'Fender Telecaster',1,1,700,'Body: Alder | Neck: Rosewood | Pickups: EMG','images/Fender Telecaster.jpg'),(3,'Fender Squire',1,1,250,'Body: Ash | Neck: Wenge | Pickups: Passive PJ','images/Fender Squire.jpg'),(4,'Gibson Les Paul',1,2,600,'Body: Mahogany | Neck: Wenge | Pickups: Gibson Active','images/Gibson Les Paul.jpg'),(5,'Ibanez RG560',1,3,600,'Body: Alder | Neck: Mahogany | Pickups: EMG','images/Ibanez RG560.jpg'),(6,'Fender Jazz Bass',2,1,900,'Body: Maple | Neck: Rosewood | Strings: 4','images/Fender Jazz Bass.jpg'),(7,'Fender Precision Bass',2,1,800,'Body: Alder | Neck: Rosewood | Strings: 4','images/Fender Precision Bass.jpg'),(8,'Cort C4H',2,5,350,'Body: Ash | Neck: Wenge | Strings: 4','images/Cort C4H.jpg'),(9,'Cort C5H',2,5,450,'Body: Mahogany | Neck: Wenge | Strings: 5','images/Cort C5H.jpg'),(10,'Music Man Stingray 6',2,6,1200,'Body: Alder | Neck: Mahogany | Strings: 6','images/Music Man Stingray 6.jpg'),(11,'Fender Hot Rod',3,1,530,'Gain: High | Lineout: Yes','images/Fender Hot Rod.jpg'),(12,'Fender Twin Reverb',3,1,400,'Gain: High | Lineout: No','images/Fender Twin Reverb.jpg'),(13,'Boss Katana',3,7,200,'Gain: Low | Lineout: No','images/Boss Katana.jpg'),(14,'Marshall AC30',3,4,470,'Gain: High | Lineout: Yes','images/Marshall AC30.jpg'),(15,'Marshall AC15',3,4,280,'Gain: Low | Lineout: No','images/Marshall AC15.jpg'),(16,'Boss Overdrive',4,7,120,'Effect type: Overdrive + Booster','images/Boss Overdrive.jpg'),(17,'Boss Distortion',4,7,100,'Effect type: Distortion','images/Boss Distortion.jpg'),(18,'Boss Delay',4,7,85,'Effect type: Delay + Looper','images/Boss Delay.jpg'),(19,'Digitech Delay',4,8,70,'Effect type: Delay + Looper','images/Digitech Delay.jpg'),(20,'Digitech Distortion',4,8,90,'Effect type: Distortion','images/Digitech Distortion.jpg');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `b11` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`b11`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'Fender'),(2,'Gibson'),(3,'Ibanez'),(4,'Marshall'),(5,'Cort'),(6,'Music Man'),(7,'Boss'),(8,'Digitech');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) NOT NULL,
  `user` int(11) NOT NULL,
  `item` int(11) NOT NULL,
  `approved` varchar(45) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`transaction_id`),
  KEY `user_fk_idx` (`user`),
  KEY `item_fk_idx` (`item`),
  CONSTRAINT `item_fk` FOREIGN KEY (`item`) REFERENCES `item` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (10,'2018-07-05',2,1,'yes'),(11,'2018-07-05',2,11,'yes'),(12,'2018-07-05',2,17,'yes'),(13,'2018-07-05',6,9,'yes'),(14,'2018-07-05',6,13,'no'),(15,'2018-07-05',6,19,'yes'),(16,'2018-07-05',7,4,'no'),(17,'2018-07-05',7,12,'yes'),(18,'2018-07-05',7,18,'yes'),(19,'2018-07-05',7,15,'no'),(20,'2018-07-05',2,14,'yes'),(21,'2018-07-05',6,7,'no'),(22,'2018-07-05',2,16,'yes'),(23,'2018-07-05',2,20,'no');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jovan','Roland','069000000','jroland@yahoo.com','Devaldova 5','admin','21232f297a57a5a743894a0e4a801fc3'),(2,'Oliver','Roland','0632131231','oliver@gmail.com','Ilije Bircanina 5','oliver','e10adc3949ba59abbe56e057f20f883e'),(6,'Aleksej','Dalifoski','0625832424','aleksej@yahoo.com','Franje Krca 8','aleksej','8dbc672497bdc46f88e864bb1121232c'),(7,'Ina','Djordjevic','0648989765','ina@gmail.com','Skadarska 5','ina','9690c3c9113ef119ccfba2ee951924a5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-10 12:42:24
