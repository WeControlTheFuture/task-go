-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: mini_oa
-- ------------------------------------------------------
-- Server version	5.7.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orgnization`
--

DROP TABLE IF EXISTS `orgnization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orgnization` (
  `pk` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `boss` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `id_UNIQUE` (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orgnization`
--

LOCK TABLES `orgnization` WRITE;
/*!40000 ALTER TABLE `orgnization` DISABLE KEYS */;
INSERT INTO `orgnization` VALUES (1,'suyi','liujun'),(2,'qixj','suyi'),(3,'zhaoyh','suyi'),(4,'tangjia','suyi'),(5,'suncy','suyi'),(6,'banyy','suyi'),(7,'hanxl','suyi'),(8,'liujd','suyi'),(9,'liyue','suyi'),(10,'zhangln','suyi'),(11,'menhj','suyi');
/*!40000 ALTER TABLE `orgnization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creater` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `assignee` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `title` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `attachment` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `start_ts` timestamp(3) NULL DEFAULT NULL,
  `finish_ts` timestamp(3) NULL DEFAULT NULL,
  `stop_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (2,'2019-07-03 13:44:00','2019-07-03 13:44:00','suyi',NULL,'没有被分配的任务','<p>a a a</p>\n',NULL,0,NULL,NULL,NULL),(7,'2019-07-03 14:32:27','2019-07-03 14:32:27','suyi','banyy','t t t','<p>试试中文</p>\n',NULL,0,NULL,NULL,'2019-07-25'),(8,'2019-07-04 14:08:40','2019-07-04 14:08:40','suyi','hanxl','明天去银行','<p>带20万去银行存上</p>\n',NULL,0,NULL,NULL,NULL),(9,'2019-07-06 11:32:30','2019-07-06 11:32:30','suyi','tangjia','去抢钱','<p>去银行抢钱</p>\n',NULL,0,NULL,NULL,NULL),(10,'2019-07-06 11:34:00','2019-07-06 11:34:00','suyi','banyy','去抢钱','<p>去银行抢20万</p>\n',NULL,0,NULL,NULL,'2019-08-17'),(11,'2019-07-06 11:34:39','2019-07-06 11:34:39','suyi','qixj','去送礼','<p>给习总书记送套房</p>\n',NULL,0,NULL,NULL,NULL),(12,'2019-07-13 13:51:05','2019-07-13 13:51:05','suyi','qixj','测试stop date','<p>去银行抢钱</p>\n',NULL,0,NULL,NULL,'2019-07-10'),(13,'2019-07-13 14:08:02','2019-07-13 14:08:02','suyi','qixj','adsa','<p>asdfasdf</p>\n',NULL,0,NULL,NULL,NULL),(14,'2019-07-13 14:27:15','2019-07-13 14:27:15','suyi','zhaoyh','测试new','<p>阿斯顿发多少</p>\n',NULL,0,NULL,NULL,'2019-07-18'),(15,'2019-07-13 14:32:18','2019-07-13 14:32:18','suyi','qixj','asdfasdf','<p>sdfasdf</p>\n',NULL,0,NULL,NULL,'2019-07-09'),(16,'2019-07-13 14:37:24','2019-07-13 14:37:24','suyi','tangjia','能清干净吗','<p>试试八</p>\n',NULL,0,NULL,NULL,'2019-07-08'),(17,'2019-07-13 14:42:10','2019-07-13 14:42:10','suyi','tangjia','asdf','<p>asdfasdf</p>\n',NULL,0,NULL,NULL,'2019-07-17'),(18,'2019-07-13 14:53:30','2019-07-13 14:53:30','suyi','tangjia','asdf','<p>asdfasdf</p>\n',NULL,0,NULL,NULL,'2019-07-05'),(19,'2019-07-13 14:54:31','2019-07-13 14:54:31','suyi','qixj','asdfsadf','',NULL,0,NULL,NULL,'2019-07-16'),(20,'2019-07-13 15:00:30','2019-07-13 15:00:30','suyi','hanxl','舒服舒服','<p>阿斯顿发送到发</p>\n',NULL,0,NULL,NULL,'2019-07-10'),(21,'2019-07-13 15:41:06','2019-07-13 15:41:06','suyi','banyy','test c','<p>asfasdfasdf</p>\n',NULL,0,NULL,NULL,'2019-07-22');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_attr`
--

DROP TABLE IF EXISTS `task_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `task_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `task_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `value` varchar(10240) COLLATE utf8_bin DEFAULT NULL,
  `user_code` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_attr`
--

LOCK TABLES `task_attr` WRITE;
/*!40000 ALTER TABLE `task_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `role` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(16) CHARACTER SET latin1 DEFAULT NULL,
  `headpic` varchar(128) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'苏怡','suyi','manager','suyi','profile/head.png'),(2,'祁新靖','qixj','employee','qixj','dist/img/user3-128x128.jpg'),(3,'赵雅慧','zhaoyh','employee','zhaoyh','dist/img/user4-128x128.jpg'),(4,'汤佳','tangjia','employee','tangjia','dist/img/user5-128x128.jpg'),(5,'孙徜徉','suncy','employee','suncy','dist/img/user6-128x128.jpg'),(6,'班宇阳','banyy','employee','banyy','dist/img/user7-128x128.jpg'),(7,'韩小丽','hanxl','employee','hanxl','dist/img/user8-128x128.jpg'),(8,'刘军东','liujd','employee','liujd','dist/img/user1-128x128.jpg'),(9,'李悦','liyue','employee','liyue','dist/img/user3-128x128.jpg'),(10,'张丽娜','zhangln','employee','zhangln','dist/img/user4-128x128.jpg'),(11,'门洪娟','menhj','employee','menhj','dist/img/user5-128x128.jpg');
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

-- Dump completed on 2019-08-03 17:40:54
