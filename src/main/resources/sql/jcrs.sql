-- MySQL dump 10.13  Distrib 5.7.11, for Linux (x86_64)
--
-- Host: localhost    Database: jcrs
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `id` varchar(64) NOT NULL,
  `roleid` varchar(64) DEFAULT NULL,
  `menuid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES ('4723c4f3-8688-48e9-b779-30159fe59d4b','97af7acb-97c8-4b22-bb93-523059e985bc','9b6cf3ad-8729-4984-a39f-74e42743000d'),('da44824c-b6ea-43a4-b9a0-6c0074b93b6d','97af7acb-97c8-4b22-bb93-523059e985bc','ad73496f-1a3d-4df1-96da-6661032a39ed');
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `href` varchar(200) DEFAULT NULL,
  `rank` varchar(2) DEFAULT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `parent` varchar(100) DEFAULT NULL,
  `sort` varchar(20) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `delete_time` varchar(64) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `delete_by` varchar(64) DEFAULT NULL,
  `permission` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('0e35b978-6db4-4f6a-adc8-4ddec04226b8','报表种类','/bbxt/bbzl','2','e44d69e0-c59f-44b7-8f27-62c64d95612a','报表系统','1','fa-circle-o',NULL,'2018-01-27 17:07:38','1','2018-01-25 23:50:00',NULL,'bbxt'),('1c398da8-83f3-4b38-8fb8-b6d498eb456b','图表管理','','1','0','顶级菜单','2','fa-circle-o',NULL,NULL,'0','2018-01-31 14:49:29',NULL,'tb'),('1ff10e25-1a63-456b-bee3-8303cc5d171d','用户管理','/system/user','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','2','fa-circle-o',NULL,'2018-01-29 17:20:15','1','2018-01-25 20:25:27',NULL,'sysuser'),('28b11dfe-7258-4eab-8784-5ac8e1bada5c','系统检测','','1','0','顶级菜单','6','fa-circle-o',NULL,NULL,'0','2018-01-31 14:58:53',NULL,'check'),('36cf430b-8647-4964-8709-50370a0f7b28','警察人事','','1','0','顶级菜单','3','fa-circle-o',NULL,NULL,'0','2018-01-31 14:49:56',NULL,'jcrs'),('3c45b582-4703-4a63-ac4c-3ae802745f68','菜单管理','/system/menu','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','1','fa-circle-o',NULL,NULL,'0','2018-01-25 20:24:36',NULL,'sysmenu'),('477c2492-8053-41d4-b647-ea4798b32c3f','警察人事','','1','0','顶级菜单','2','fa-user-plus',NULL,'2018-01-27 17:07:28','1','2018-01-25 20:35:12',NULL,'jcrs'),('65fe26cf-1767-41f0-8d80-b2a9714b1345','图表生成','','1','0','顶级菜单','4','fa-circle-o',NULL,NULL,'0','2018-01-31 14:57:45',NULL,'tbsc'),('780df776-6a3b-4b01-a387-4a8412f2a3c6','机构管理','/system/org','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','4','fa-circle-o',NULL,NULL,'0','2018-01-31 09:58:30',NULL,'org'),('8d54b4c0-855b-4b24-b64c-f86b14c12b07','字典管理','/system/dict','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','5','fa-circle-o',NULL,NULL,'0','2018-01-31 09:59:00',NULL,'dict'),('9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','','1','0','顶级菜单','1','fa-desktop',NULL,NULL,'0','2018-01-25 17:11:31',NULL,'sys'),('a0225945-7059-4810-92e9-3b28c669da74','警察基本信息','/jcrs/jcjbxx/','2','477c2492-8053-41d4-b647-ea4798b32c3f','警察人事','1','fa-user',NULL,'2018-01-27 16:20:52','1','2018-01-25 20:40:38',NULL,'jcjbxx'),('a842a167-ca27-4c33-ac7e-7de8def3ff27','角色管理','/system/role/index','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','3','fa-circle-o',NULL,'2018-01-26 19:13:54','1','2018-01-25 23:40:17',NULL,'role'),('ad73496f-1a3d-4df1-96da-6661032a39ed','用户管理','/system/user','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','2','fa-circle-o',NULL,NULL,'0','2018-01-29 17:25:56',NULL,'sys_user'),('c0e9a952-5212-4f1c-b130-d15284e50930','主页','/index','2','0','顶级菜单','0','fa-tachometer',NULL,'2018-01-29 15:17:07','1',NULL,NULL,'index'),('dc553072-c46b-4f60-80eb-cfbd8f710c80','图表管理','','1','0','顶级菜单','4','fa-bar-chart',NULL,'2018-01-27 17:07:46','1','2018-01-26 12:42:33',NULL,'chart'),('df70a81d-b69e-4d59-9b6d-f9cbe58674b4','代码生成','','1','0','顶级菜单','5','fa-circle-o',NULL,NULL,'0','2018-01-31 14:58:01',NULL,'code'),('e44d69e0-c59f-44b7-8f27-62c64d95612a','报表系统','','1','0','顶级菜单','3','fa-file-excel-o',NULL,'2018-01-27 17:07:41','1','2018-01-25 23:49:06',NULL,'bbxt'),('f07d2740-d6c7-48e8-9256-ce7c1be33138','角色管理','/system/role','2','9b6cf3ad-8729-4984-a39f-74e42743000d','系统管理','3','fa-circle-o',NULL,NULL,'0','2018-01-26 19:13:15',NULL,'role');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `ename` varchar(64) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `delete_by` varchar(64) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('97af7acb-97c8-4b22-bb93-523059e985bc','运维角色','maintenance',NULL,NULL,'0','2018-02-02 07:21:43',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `logname` varchar(50) DEFAULT NULL,
  `pass` varchar(200) DEFAULT NULL,
  `picture` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `delete_by` varchar(64) DEFAULT NULL,
  `del_flag` varchar(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('19903f8f-c8c4-4924-9c61-7aa8ce19fc80','test9','test9',NULL,NULL,'test9@qq.com','test9',NULL,NULL,'0','2018-01-30 21:03:41','2018-01-31 14:47:33'),('20275b0d-ba5a-4448-871a-0e376273adb0','test8','test8',NULL,NULL,'test8@qq.com','test8',NULL,NULL,'0','2018-01-30 21:03:20','2018-01-31 14:59:59'),('34929498-0e91-462d-991c-cf5df182b466',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','2018-02-19 17:43:27','2018-02-19 18:00:55'),('4653f6c8-1297-42bc-8f44-fe51b9f12e57','2877408638','2877408638','2877408638',NULL,'2877408638@qq.com','2877408638',NULL,NULL,'0','2018-02-19 18:01:20',NULL),('46895dcb-a63c-45da-a1c7-265128908aea','test7','test7',NULL,NULL,'test7@qq.com','test7',NULL,NULL,'0','2018-01-30 21:03:04','2018-01-31 15:00:22'),('4a18e66f-a60d-4961-92a4-4a2d87d26408','廖振钦','996298643',NULL,NULL,'996298643@qq.com','15086844388',NULL,NULL,'0','2018-01-30 19:58:25',NULL),('874e61fa-0a97-4ead-a2de-44d74e650afd','test6','test6',NULL,NULL,'test6@qq.com','test6',NULL,NULL,'0','2018-01-30 21:02:47','2018-01-31 14:47:51'),('9c072dd8-7a12-4e0f-b6b8-f06c93d9729c','test1','test1',NULL,NULL,'test1@qq.com','test1',NULL,NULL,'0','2018-01-30 21:01:30','2018-01-31 14:55:17'),('b4d9ea40-ea08-4b91-9d7e-86e9314cc828','test4','test4',NULL,NULL,'test4@qq.com','test4',NULL,NULL,'0','2018-01-30 21:02:11','2018-01-31 14:50:50'),('bedc43b2-27d9-4be6-8f49-3aa415b9db7c','test2','test2',NULL,NULL,'test2@qq.com','test2',NULL,NULL,'0','2018-01-30 21:01:45','2018-01-31 14:52:56'),('c30d0f3b-552c-4916-9d4b-2dbce1fb4243','admin','admin','admin',NULL,'996298643@qq.com','18223484385',NULL,NULL,'0','2018-01-30 19:46:08',NULL),('d6f24895-b510-4d0b-808f-b5d42d876bbb','test5','test5',NULL,NULL,'test5@qq.com','test5',NULL,NULL,'0','2018-01-30 21:02:24','2018-01-31 14:50:40'),('ecda2c1a-acd2-4619-8cf0-6750c39b1854','test3','test3',NULL,NULL,'test3@qq.com','test3',NULL,NULL,'0','2018-01-30 21:01:57','2018-01-31 14:51:31');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` varchar(64) NOT NULL,
  `userid` varchar(64) DEFAULT NULL,
  `roleid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1','4653f6c8-1297-42bc-8f44-fe51b9f12e57','97af7acb-97c8-4b22-bb93-523059e985bc');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-21 20:01:02
