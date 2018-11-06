-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: freeread_db
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `novel_class`
--

DROP TABLE IF EXISTS `novel_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_class` (
  `novel_class_id` int(11) NOT NULL,
  `novel_class_name` varchar(50) NOT NULL,
  PRIMARY KEY (`novel_class_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_content`
--

DROP TABLE IF EXISTS `novel_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_content` (
  `novel_id` varchar(50) NOT NULL,
  `novel_chapter_index` int(11) NOT NULL,
  `novel_chapter_content` text NOT NULL,
  `novel_chapter_name` varchar(255) NOT NULL,
  PRIMARY KEY (`novel_id`,`novel_chapter_index`) USING BTREE,
  KEY `index_id` (`novel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_head`
--

DROP TABLE IF EXISTS `novel_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_head` (
  `novel_id` varchar(50) NOT NULL,
  `novel_name` varchar(255) NOT NULL,
  `novel_picture` varchar(255) DEFAULT NULL,
  `novel_desc` varchar(255) DEFAULT NULL,
  `novel_content_table_name` varchar(255) NOT NULL,
  `novel_class_id_1` int(11) NOT NULL,
  `novel_class_id_2` int(11) DEFAULT NULL,
  `novel_class_id_3` int(11) DEFAULT NULL,
  `novel_status` int(11) NOT NULL,
  `novel_chapter_num` int(11) NOT NULL,
  `novel_author` varchar(50) DEFAULT NULL,
  `novel_from` int(11) DEFAULT '0',
  `novel_popularity` int(11) DEFAULT '0',
  PRIMARY KEY (`novel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_shelf`
--

DROP TABLE IF EXISTS `novel_shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_shelf` (
  `user_name` varchar(50) NOT NULL,
  `novel_id` varchar(50) NOT NULL,
  `novel_name` varchar(255) NOT NULL,
  `last_read_chapter` int(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`,`novel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_user`
--

DROP TABLE IF EXISTS `novel_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_user` (
  `user_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `user_pass` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_role` int(255) NOT NULL,
  `user_info_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_user_info`
--

DROP TABLE IF EXISTS `novel_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_user_info` (
  `user_info_id` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novel_wish_list`
--

DROP TABLE IF EXISTS `novel_wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novel_wish_list` (
  `user_name` varchar(50) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `novel_name` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `is_notify` varchar(10) DEFAULT NULL,
  `is_exist` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_name`,`user_email`,`novel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-06  4:39:10