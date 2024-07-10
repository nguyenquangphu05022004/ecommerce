CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `basket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `stock_id` bigint(20) DEFAULT NULL,
  `stock_classification_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK810a8gq30miyp6j1eub97qm6k` (`user_id`),
  KEY `FKqqkbhdmxbv3jissqw7vidi9kb` (`stock_id`),
  KEY `FK6ftq52b01fff4srfcqaj860em` (`stock_classification_id`),
  CONSTRAINT `FK6ftq52b01fff4srfcqaj860em` FOREIGN KEY (`stock_classification_id`) REFERENCES `stock_classifications` (`id`),
  CONSTRAINT `FK810a8gq30miyp6j1eub97qm6k` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqqkbhdmxbv3jissqw7vidi9kb` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (76,'ADMIN','2024-06-22 16:10:05.928946','ADMIN','2024-06-22 17:05:09.702420',5,9,14,7),(78,'ADMIN','2024-06-22 16:10:16.792850','ADMIN','2024-06-22 16:10:20.027991',2,9,4,4),(79,'ADMIN','2024-06-22 16:41:34.014575','ADMIN','2024-06-22 16:41:34.014575',1,9,22,40),(82,'ADMIN','2024-06-22 17:05:20.207430','ADMIN','2024-06-22 17:05:20.207430',1,9,10,32),(84,'ADMIN','2024-06-22 17:19:04.061413','ADMIN','2024-06-22 17:19:04.061413',1,4,31,47),(85,'ADMIN','2024-06-30 16:04:54.223265','ADMIN','2024-06-30 16:04:54.223265',1,9,4,6);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6b3bn760mqxmhflt089q8ba00` (`image_id`),
  CONSTRAINT `FKqhmw54g2p4xu0k71mblvlqfvi` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'ADMIN','2024-03-14 18:58:46.871492','ADMIN','2024-06-16 09:34:19.927526','Đồ thể thao',79),(2,'ADMIN','2024-03-16 09:59:18.775572','ADMIN','2024-06-16 09:43:36.958659','Máy tính',80),(3,'ADMIN','2024-03-28 22:54:44.024534','ADMIN','2024-06-16 09:44:23.290911','Điện thoại',81),(4,'ADMIN','2024-04-03 16:39:50.408472','ADMIN','2024-06-16 09:45:05.104330','Đồ gia dụng',82),(5,'ADMIN','2024-04-03 16:57:38.554314','ADMIN','2024-06-16 09:45:43.044887','Giày',83),(6,'ADMIN','2024-04-04 09:01:55.715264','ADMIN','2024-06-16 09:46:15.780636','Đồ dùng học tập',84);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_messages`
--

DROP TABLE IF EXISTS `chat_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chat_messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `sender_conversation` bigint(20) DEFAULT NULL,
  `user_sender_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5etwoxgy36uuxyikh994ftrly` (`sender_conversation`),
  KEY `FKhe9tb6fi5d2yus5jv8jg3tkpc` (`user_sender_id`),
  CONSTRAINT `FK5etwoxgy36uuxyikh994ftrly` FOREIGN KEY (`sender_conversation`) REFERENCES `conversations` (`id`),
  CONSTRAINT `FKhe9tb6fi5d2yus5jv8jg3tkpc` FOREIGN KEY (`user_sender_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_messages`
--

LOCK TABLES `chat_messages` WRITE;
/*!40000 ALTER TABLE `chat_messages` DISABLE KEYS */;
INSERT INTO `chat_messages` VALUES (1,NULL,'2024-06-09 16:02:55.000000',NULL,'2024-06-09 16:02:54.000000','Hello',1,4),(2,NULL,'2024-06-09 16:24:23.000000',NULL,'2024-06-09 16:24:21.000000','Xin chao',1,3),(3,'ADMIN','2024-06-09 16:40:29.438860','ADMIN','2024-06-09 16:40:29.438860','lo',1,4),(4,'ADMIN','2024-06-09 16:56:28.465844','ADMIN','2024-06-09 16:56:28.465844','lo',1,4),(5,'ADMIN','2024-06-09 16:57:43.359171','ADMIN','2024-06-09 16:57:43.359171','zo',1,4),(6,'ADMIN','2024-06-09 16:57:56.229551','ADMIN','2024-06-09 16:57:56.229551','ha',1,4),(7,'ADMIN','2024-06-09 16:57:58.709448','ADMIN','2024-06-09 16:57:58.709448','ha',1,4),(8,'ADMIN','2024-06-09 16:58:03.277097','ADMIN','2024-06-09 16:58:03.277097','cai gi',1,4),(9,'ADMIN','2024-06-09 16:59:56.731340','ADMIN','2024-06-09 16:59:56.731340','online khong cau',1,4),(10,'ADMIN','2024-06-09 17:01:26.522945','ADMIN','2024-06-09 17:01:26.522945','he',1,4),(11,'ADMIN','2024-06-09 17:01:37.114559','ADMIN','2024-06-09 17:01:37.114559','alo',1,4),(12,'ADMIN','2024-06-09 17:03:28.872750','ADMIN','2024-06-09 17:03:28.872750','to chao cau',1,4),(13,'ADMIN','2024-06-09 17:03:33.651978','ADMIN','2024-06-09 17:03:33.651978','online khong',1,4),(14,'ADMIN','2024-06-09 17:04:05.537119','ADMIN','2024-06-09 17:04:05.537119','cau oi',1,4),(15,'ADMIN','2024-06-09 17:05:47.075227','ADMIN','2024-06-09 17:05:47.075227','lo',1,4),(16,'ADMIN','2024-06-09 17:06:42.560451','ADMIN','2024-06-09 17:06:42.560451','co chuyen gi vay ban',1,3),(17,'ADMIN','2024-06-09 17:07:09.346648','ADMIN','2024-06-09 17:07:09.346648','toi vua test chuc nang chat application day',1,4),(18,'ADMIN','2024-06-09 17:07:13.366093','ADMIN','2024-06-09 17:07:13.366093','ok ngon',1,3),(19,'ADMIN','2024-06-10 22:29:46.484216','ADMIN','2024-06-10 22:29:46.484216','lo',1,4),(20,'ADMIN','2024-06-11 14:03:01.297989','ADMIN','2024-06-11 14:03:01.297989','lo',1,4),(21,'ADMIN','2024-06-11 14:06:33.270813','ADMIN','2024-06-11 14:06:33.270813','hell',1,4),(22,'ADMIN','2024-06-11 14:09:13.812592','ADMIN','2024-06-11 14:09:13.812592','zo',1,4),(23,'ADMIN','2024-06-11 15:01:32.338380','ADMIN','2024-06-11 15:01:32.338380','zo',1,4),(24,'ADMIN','2024-06-11 15:01:34.770132','ADMIN','2024-06-11 15:01:34.770132','hehe',1,4),(25,'ADMIN','2024-06-11 15:50:12.459784','ADMIN','2024-06-11 15:50:12.459784','hell',1,4),(26,'ADMIN','2024-06-11 15:50:18.286099','ADMIN','2024-06-11 15:50:18.286099','zo',1,4),(27,'ADMIN','2024-06-11 15:51:02.552090','ADMIN','2024-06-11 15:51:02.552090','hel',1,4),(28,'ADMIN','2024-06-11 15:51:04.037005','ADMIN','2024-06-11 15:51:04.037005','zl',1,4),(29,'ADMIN','2024-06-11 15:53:09.771435','ADMIN','2024-06-11 15:53:09.771435','hello',1,4),(30,'ADMIN','2024-06-11 15:58:45.711097','ADMIN','2024-06-11 15:58:45.711097','hell',2,4),(31,'ADMIN','2024-06-11 16:25:36.625968','ADMIN','2024-06-11 16:25:36.625968','welcome to my channel',2,4),(32,'ADMIN','2024-06-11 16:27:14.368054','ADMIN','2024-06-11 16:27:14.368054','gi vay',2,6),(33,'ADMIN','2024-06-11 16:27:21.880250','ADMIN','2024-06-11 16:27:21.880250','no',2,4),(34,'ADMIN','2024-06-11 16:27:27.680726','ADMIN','2024-06-11 16:27:27.680726','zo',2,4),(35,'ADMIN','2024-06-11 16:27:34.361991','ADMIN','2024-06-11 16:27:34.361991','hell',2,4),(36,'ADMIN','2024-06-11 16:27:38.362465','ADMIN','2024-06-11 16:27:38.362465','gi',2,6),(38,'ADMIN','2024-06-11 16:42:59.535360','ADMIN','2024-06-11 16:42:59.535360','xin chào',2,4),(39,'ADMIN','2024-06-11 16:43:13.451983','ADMIN','2024-06-11 16:43:13.451983','có chuyện gì vậy?',2,6),(40,'ADMIN','2024-06-11 16:43:27.249769','ADMIN','2024-06-11 16:43:27.249769','alg',2,4),(41,'ADMIN','2024-06-11 16:43:30.907992','ADMIN','2024-06-11 16:43:30.907992','v',2,6),(42,'ADMIN','2024-06-11 16:43:40.101226','ADMIN','2024-06-11 16:43:40.101226','tôi lang',2,6),(43,'ADMIN','2024-06-11 16:43:43.323551','ADMIN','2024-06-11 16:43:43.323551','ok',2,4),(46,'ADMIN','2024-06-11 16:58:31.112150','ADMIN','2024-06-11 16:58:31.112150','xin chao',8,6),(47,'ADMIN','2024-06-11 16:58:38.612862','ADMIN','2024-06-11 16:58:38.612862','hello',8,3),(48,'ADMIN','2024-06-11 16:58:45.406171','ADMIN','2024-06-11 16:58:45.406171','ok',8,3),(49,'ADMIN','2024-06-11 16:58:48.757528','ADMIN','2024-06-11 16:58:48.757528','xin chao',8,6),(50,'ADMIN','2024-06-11 16:58:51.318417','ADMIN','2024-06-11 16:58:51.318417','hehe',8,6),(51,'ADMIN','2024-06-11 16:59:01.417174','ADMIN','2024-06-11 16:59:01.417174','ok',8,3),(52,'ADMIN','2024-06-11 16:59:07.536503','ADMIN','2024-06-11 16:59:07.536503','yes',8,6),(53,'ADMIN','2024-06-11 16:59:23.359364','ADMIN','2024-06-11 16:59:23.359364','sao nhieu vay',8,6),(54,'ADMIN','2024-06-11 17:00:02.665539','ADMIN','2024-06-11 17:00:02.665539','vc',8,3),(55,'ADMIN','2024-06-11 17:00:09.460018','ADMIN','2024-06-11 17:00:09.460018','vcl',8,3),(56,'ADMIN','2024-06-11 17:01:27.683793','ADMIN','2024-06-11 17:01:27.683793','lo',8,3),(57,'ADMIN','2024-06-11 17:01:44.192094','ADMIN','2024-06-11 17:01:44.192094','chuyen gi',8,6),(58,'ADMIN','2024-06-11 17:01:58.131994','ADMIN','2024-06-11 17:01:58.131994','j',8,3),(59,'ADMIN','2024-06-11 17:02:11.575133','ADMIN','2024-06-11 17:02:11.575133','huhu',8,6),(60,'ADMIN','2024-06-11 17:02:55.335101','ADMIN','2024-06-11 17:02:55.335101','lag',2,6),(61,'ADMIN','2024-06-11 17:03:10.966953','ADMIN','2024-06-11 17:03:10.966953','zo',8,3),(62,'ADMIN','2024-06-11 17:03:18.018582','ADMIN','2024-06-11 17:03:18.018582','toi lang',8,6),(87,'ADMIN','2024-06-11 17:32:21.728964','ADMIN','2024-06-11 17:32:21.728964','lo hang hong',19,6),(88,'ADMIN','2024-06-11 17:32:26.196186','ADMIN','2024-06-11 17:32:26.196186','sao vay',19,8),(89,'ADMIN','2024-06-11 17:32:31.658999','ADMIN','2024-06-11 17:32:31.658999','bi hong anh oi',19,6),(90,'ADMIN','2024-06-11 17:32:54.266794','ADMIN','2024-06-11 17:32:54.266794','hong cai deo gi',19,8),(91,'ADMIN','2024-06-11 17:33:37.598969','ADMIN','2024-06-11 17:33:37.598969','ban oi',20,4),(92,'ADMIN','2024-06-11 17:33:44.606082','ADMIN','2024-06-11 17:33:44.606082','chuyen gi vay ban',20,8),(93,'ADMIN','2024-06-11 17:34:07.552090','ADMIN','2024-06-11 17:34:07.552090','anh oi',8,6),(94,'ADMIN','2024-06-11 17:34:41.048492','ADMIN','2024-06-11 17:34:41.048492','anh oi',2,6),(95,'ADMIN','2024-06-11 17:35:00.888497','ADMIN','2024-06-11 17:35:00.888497','gi',2,4),(96,'ADMIN','2024-06-11 17:35:20.091258','ADMIN','2024-06-11 17:35:20.091258','lo',20,4),(97,'ADMIN','2024-06-11 17:35:49.406257','ADMIN','2024-06-11 17:35:49.406257','gi',2,4),(98,'ADMIN','2024-06-11 17:35:50.656812','ADMIN','2024-06-11 17:35:50.656812','alo',2,4),(99,'ADMIN','2024-06-12 14:31:37.625045','ADMIN','2024-06-12 14:31:37.625045','hello',19,8),(100,'ADMIN','2024-06-12 14:31:48.803788','ADMIN','2024-06-12 14:31:48.803788','xin chao',19,6),(101,'ADMIN','2024-06-12 14:32:01.881779','ADMIN','2024-06-12 14:32:01.881779','chuye gi',19,6),(102,'ADMIN','2024-06-12 14:32:05.345978','ADMIN','2024-06-12 14:32:05.345978','noet',19,8),(103,'ADMIN','2024-06-12 14:50:44.331971','ADMIN','2024-06-12 14:50:44.331971','zo',19,8),(104,'ADMIN','2024-06-12 14:50:57.104117','ADMIN','2024-06-12 14:50:57.104117','hel',19,6),(105,'ADMIN','2024-06-12 14:51:04.067454','ADMIN','2024-06-12 14:51:04.067454','lag',19,6),(106,'ADMIN','2024-06-12 14:51:06.968020','ADMIN','2024-06-12 14:51:06.968020','zo',19,8),(107,'ADMIN','2024-06-12 14:51:22.087718','ADMIN','2024-06-12 14:51:22.087718','asifaif',19,6),(108,'ADMIN','2024-06-12 14:51:23.549021','ADMIN','2024-06-12 14:51:23.549021','asd',19,8),(109,'ADMIN','2024-06-12 14:57:21.089654','ADMIN','2024-06-12 14:57:21.089654','xin chao',19,8),(110,'ADMIN','2024-06-12 14:57:40.834504','ADMIN','2024-06-12 14:57:40.834504','gi vay',19,6),(111,'ADMIN','2024-06-12 14:58:24.492720','ADMIN','2024-06-12 14:58:24.492720','chao cau',19,6),(112,'ADMIN','2024-06-12 14:58:57.193231','ADMIN','2024-06-12 14:58:57.193231','helo',19,6),(113,'ADMIN','2024-06-12 14:59:03.935270','ADMIN','2024-06-12 14:59:03.935270','o',19,8),(114,'ADMIN','2024-06-12 14:59:08.896541','ADMIN','2024-06-12 14:59:08.896541','zo',19,8),(115,'ADMIN','2024-06-12 14:59:19.090512','ADMIN','2024-06-12 14:59:19.090512','hi',19,8),(116,'ADMIN','2024-06-12 14:59:27.022089','ADMIN','2024-06-12 14:59:27.022089','xin ',19,8),(117,'ADMIN','2024-06-12 14:59:31.245381','ADMIN','2024-06-12 14:59:31.245381','he',19,6),(118,'ADMIN','2024-06-12 14:59:34.377718','ADMIN','2024-06-12 14:59:34.377718','',19,8),(119,'ADMIN','2024-06-12 14:59:38.369702','ADMIN','2024-06-12 14:59:38.369702','what?',19,6),(120,'ADMIN','2024-06-12 14:59:50.960546','ADMIN','2024-06-12 14:59:50.960546','wekrrrrrrrrrrrrrrrrrrrkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkefkalf asfasfaslf asgajgladkgd gsdjafjas fdsjgas fadjgds gad',19,6),(121,'ADMIN','2024-06-12 15:56:39.178741','ADMIN','2024-06-12 15:56:39.178741','za',20,8),(122,'ADMIN','2024-06-15 10:50:57.280125','ADMIN','2024-06-15 10:50:57.280125','hello',20,4),(123,'ADMIN','2024-06-16 09:08:29.914139','ADMIN','2024-06-16 09:08:29.914139','hell',1,4),(124,'ADMIN','2024-06-16 09:08:33.020794','ADMIN','2024-06-16 09:08:33.020794','zo',20,4),(125,'ADMIN','2024-06-16 09:09:05.955003','ADMIN','2024-06-16 09:09:05.955003','hey',2,6),(126,'ADMIN','2024-06-16 09:48:01.214981','ADMIN','2024-06-16 09:48:01.214981','zo',21,2),(127,'ADMIN','2024-06-16 09:48:09.157948','ADMIN','2024-06-16 09:48:09.157948','hello',22,2),(128,'ADMIN','2024-06-16 09:48:36.470168','ADMIN','2024-06-16 09:48:36.470168','xin chao',23,2),(129,'ADMIN','2024-06-16 09:48:45.849145','ADMIN','2024-06-16 09:48:45.849145','afsf',22,2),(130,'ADMIN','2024-06-16 09:48:48.392777','ADMIN','2024-06-16 09:48:48.392777','sdfsdf',21,2),(131,'ADMIN','2024-06-16 09:49:03.149576','ADMIN','2024-06-16 09:49:03.149576','he',24,2),(132,'ADMIN','2024-06-16 09:49:07.295171','ADMIN','2024-06-16 09:49:07.295171','holla',24,2),(133,'ADMIN','2024-06-16 09:49:38.108048','ADMIN','2024-06-16 09:49:38.108048','f',24,8),(134,'ADMIN','2024-06-16 09:49:40.467602','ADMIN','2024-06-16 09:49:40.467602','f',23,8),(135,'ADMIN','2024-06-16 09:49:43.352354','ADMIN','2024-06-16 09:49:43.352354','s',19,8),(136,'ADMIN','2024-06-17 13:27:53.905046','ADMIN','2024-06-17 13:27:53.905046','hel',24,2),(137,'ADMIN','2024-06-17 13:27:58.111407','ADMIN','2024-06-17 13:27:58.111407','welcome',23,2),(143,'ADMIN','2024-06-17 13:42:19.949568','ADMIN','2024-06-17 13:42:19.949568','helo',25,9),(144,'ADMIN','2024-06-17 13:43:54.722939','ADMIN','2024-06-17 13:43:54.722939','cau oi',26,9),(145,'ADMIN','2024-06-17 13:44:16.243667','ADMIN','2024-06-17 13:44:16.243667','alo',26,9),(146,'ADMIN','2024-06-17 13:44:40.015039','ADMIN','2024-06-17 13:44:40.015039','zo',27,9),(147,'ADMIN','2024-06-17 13:46:00.541810','ADMIN','2024-06-17 13:46:00.541810','sao',27,3),(148,'ADMIN','2024-06-17 13:47:02.049255','ADMIN','2024-06-17 13:47:02.049255','tôi vừa mua sản phẩm bên bạn hiện đang gặp lỗi',27,9),(149,'ADMIN','2024-06-17 13:47:24.885271','ADMIN','2024-06-17 13:47:24.885271','bạn có thể gửi cho chúng tôi hình ảnh về sản phẩm mà bạn đã mua không?',27,3),(150,'ADMIN','2024-06-17 13:47:37.366300','ADMIN','2024-06-17 13:47:37.366300','đợi tý ạ',27,9),(151,'ADMIN','2024-06-17 14:03:37.889875','ADMIN','2024-06-17 14:03:37.889875','tt',27,9),(152,'ADMIN','2024-06-17 14:24:54.345072','ADMIN','2024-06-17 14:24:54.345072','zo',27,9),(153,'ADMIN','2024-06-17 14:25:29.594650','ADMIN','2024-06-17 14:25:29.594650','zo',27,9),(154,'ADMIN','2024-06-17 14:26:30.370536','ADMIN','2024-06-17 14:26:30.370536','hello',27,9),(155,'ADMIN','2024-06-17 14:26:38.659917','ADMIN','2024-06-17 14:26:38.659917','zo',27,9),(156,'ADMIN','2024-06-17 14:28:26.454519','ADMIN','2024-06-17 14:28:26.454519','xi n chao',27,9),(157,'ADMIN','2024-06-17 16:21:39.022088','ADMIN','2024-06-17 16:21:39.022088','hehe',27,9),(158,'ADMIN','2024-06-17 16:21:42.997298','ADMIN','2024-06-17 16:21:42.997298','zo',25,9),(159,'ADMIN','2024-06-17 16:21:45.744245','ADMIN','2024-06-17 16:21:45.744245','zo',26,9),(160,'ADMIN','2024-06-17 17:16:45.267526','ADMIN','2024-06-17 17:16:45.267526','alo ban oi',27,3),(161,'ADMIN','2024-06-19 14:45:41.704321','ADMIN','2024-06-19 14:45:41.704321','alo',26,9),(162,'ADMIN','2024-06-19 14:47:58.251135','ADMIN','2024-06-19 14:47:58.251135','xin chao',26,9),(163,'ADMIN','2024-06-19 14:54:43.978807','ADMIN','2024-06-19 14:54:43.978807','hel',27,9),(164,'ADMIN','2024-06-19 14:54:53.730694','ADMIN','2024-06-19 14:54:53.730694','alo',25,9),(165,'ADMIN','2024-06-19 15:21:50.114542','ADMIN','2024-06-19 15:21:50.114542','alo',25,9),(166,'ADMIN','2024-06-19 15:21:56.962162','ADMIN','2024-06-19 15:21:56.962162','xin chao',25,9),(167,'ADMIN','2024-06-19 15:21:59.346319','ADMIN','2024-06-19 15:21:59.346319','alo alo',25,9),(168,'ADMIN','2024-06-19 15:33:12.266983','ADMIN','2024-06-19 15:33:12.266983','hello',26,9),(169,'ADMIN','2024-06-19 15:43:51.466123','ADMIN','2024-06-19 15:43:51.466123','hello',25,9),(170,'ADMIN','2024-06-19 15:43:53.362612','ADMIN','2024-06-19 15:43:53.362612','zozo',25,9),(171,'ADMIN','2024-06-19 15:44:03.306972','ADMIN','2024-06-19 15:44:03.306972','alo ban oi',27,9),(172,'ADMIN','2024-06-19 15:49:18.214558','ADMIN','2024-06-19 15:49:18.214558','chao cau',25,9),(173,'ADMIN','2024-06-19 15:51:48.962440','ADMIN','2024-06-19 15:51:48.962440','cau oi',25,9),(174,'ADMIN','2024-06-19 15:51:50.771756','ADMIN','2024-06-19 15:51:50.771756','gi',25,4),(175,'ADMIN','2024-06-19 15:56:11.551742','ADMIN','2024-06-19 15:56:11.551742','xin chao',20,4),(176,'ADMIN','2024-06-19 16:02:58.560604','ADMIN','2024-06-19 16:02:58.560604','hehe',20,4),(177,'ADMIN','2024-06-19 16:03:26.189254','ADMIN','2024-06-19 16:03:26.189254','hehe',28,9),(178,'ADMIN','2024-06-19 16:18:03.228169','ADMIN','2024-06-19 16:18:03.228169','xin chao',29,10),(179,'ADMIN','2024-06-21 15:53:50.104239','ADMIN','2024-06-21 15:53:50.104239','xin chao',20,8),(180,'ADMIN','2024-06-21 17:32:43.851263','ADMIN','2024-06-21 17:32:43.851263','hello',28,9),(181,'ADMIN','2024-06-21 17:35:15.662614','ADMIN','2024-06-21 17:35:15.662614','hello',28,9),(182,'ADMIN','2024-06-21 17:39:25.007415','ADMIN','2024-06-21 17:39:25.007415','xin chao',28,9),(183,'ADMIN','2024-06-21 17:39:56.492935','ADMIN','2024-06-21 17:39:56.492935','chao',28,9),(184,'ADMIN','2024-06-21 17:42:26.418341','ADMIN','2024-06-21 17:42:26.418341','hello',28,9),(185,'ADMIN','2024-06-21 17:42:29.796918','ADMIN','2024-06-21 17:42:29.796918','xin chao',28,9),(186,'ADMIN','2024-06-21 17:43:04.944488','ADMIN','2024-06-21 17:43:04.944488','lo',25,9),(187,'ADMIN','2024-06-21 17:43:17.230589','ADMIN','2024-06-21 17:43:17.230589','zo',25,9),(188,'ADMIN','2024-06-21 18:23:12.209871','ADMIN','2024-06-21 18:23:12.209871','hell',28,9),(189,'ADMIN','2024-06-21 18:26:52.844418','ADMIN','2024-06-21 18:26:52.844418','hello',28,9),(190,'ADMIN','2024-06-21 18:28:44.249715','ADMIN','2024-06-21 18:28:44.249715','hello friend',28,9),(191,'ADMIN','2024-06-21 18:28:55.141037','ADMIN','2024-06-21 18:28:55.141037','my image',28,9),(192,'ADMIN','2024-06-21 18:29:37.862214','ADMIN','2024-06-21 18:29:37.862214','image second',28,9),(193,'ADMIN','2024-06-21 18:30:41.567026','ADMIN','2024-06-21 18:30:41.567026','3',28,9),(194,'ADMIN','2024-06-21 18:32:35.757974','ADMIN','2024-06-21 18:32:35.757974','test',28,9),(195,'ADMIN','2024-06-21 18:32:58.388884','ADMIN','2024-06-21 18:32:58.388884','test1',28,9),(196,'ADMIN','2024-06-21 18:33:21.736468','ADMIN','2024-06-21 18:33:21.736468','test2',28,9),(197,'ADMIN','2024-06-21 18:33:30.474580','ADMIN','2024-06-21 18:33:30.474580','test3',28,9),(198,'ADMIN','2024-06-21 18:33:35.438158','ADMIN','2024-06-21 18:33:35.438158','test4',28,9),(199,'ADMIN','2024-06-22 13:54:56.043770','ADMIN','2024-06-22 13:54:56.043770','hello',26,9),(200,'ADMIN','2024-06-22 13:54:58.957087','ADMIN','2024-06-22 13:54:58.957087','xin chao',26,9),(201,'ADMIN','2024-06-22 13:56:16.199537','ADMIN','2024-06-22 13:56:16.199537','tôi vừa mua hàng bên shop, sau khi kiểm tra nó bị lỗi',25,9),(202,'ADMIN','2024-06-22 13:56:38.677825','ADMIN','2024-06-22 13:56:38.677825','lỗi ở đâu',25,4),(203,'ADMIN','2024-06-22 13:56:56.191668','ADMIN','2024-06-22 13:56:56.191668','để tôi gửi ảnh',25,9),(204,'ADMIN','2024-06-22 13:57:05.461585','ADMIN','2024-06-22 13:57:05.461585','ok',25,4),(205,'ADMIN','2024-06-22 13:57:19.361273','ADMIN','2024-06-22 13:57:19.361273','l',25,9),(206,'ADMIN','2024-06-22 13:57:29.300405','ADMIN','2024-06-22 13:57:29.300405','lo',25,9),(207,'ADMIN','2024-06-22 13:57:37.437892','ADMIN','2024-06-22 13:57:37.437892','gửi đi',25,4),(208,'ADMIN','2024-06-22 13:57:45.142877','ADMIN','2024-06-22 13:57:45.142877','đây',25,9),(209,'ADMIN','2024-06-22 14:00:36.156135','ADMIN','2024-06-22 14:00:36.156135','ảnh đéo gì vậy',25,4),(210,'ADMIN','2024-06-22 14:01:02.851133','ADMIN','2024-06-22 14:01:02.851133','đây bạn',25,9),(211,'ADMIN','2024-06-22 14:01:24.924143','ADMIN','2024-06-22 14:01:24.924143','anime vậy',25,4),(212,'ADMIN','2024-06-22 14:01:36.839137','ADMIN','2024-06-22 14:01:36.839137','lỗi',25,9),(213,'ADMIN','2024-06-22 14:01:40.437845','ADMIN','2024-06-22 14:01:40.437845','gửi đi',25,4),(214,'ADMIN','2024-06-22 14:02:02.937256','ADMIN','2024-06-22 14:02:02.937256','alo',25,4),(215,'ADMIN','2024-06-22 14:02:07.773052','ADMIN','2024-06-22 14:02:07.773052','a',25,9),(216,'ADMIN','2024-06-22 14:02:28.810544','ADMIN','2024-06-22 14:02:28.810544','zzzzzzz',25,4),(217,'ADMIN','2024-06-22 14:03:03.974844','ADMIN','2024-06-22 14:03:03.974844','alo',25,9),(218,'ADMIN','2024-06-22 14:03:09.896330','ADMIN','2024-06-22 14:03:09.896330','giì',25,4),(219,'ADMIN','2024-06-22 14:04:51.644511','ADMIN','2024-06-22 14:04:51.644511','hello friend',25,4),(220,'ADMIN','2024-06-22 14:05:16.356291','ADMIN','2024-06-22 14:05:16.356291','gửi cho tôi cái ảnh mà bạn bảo lỗi để chúng tôi xem lại?',25,4),(221,'ADMIN','2024-06-22 14:05:33.732171','ADMIN','2024-06-22 14:05:33.732171','ak ok để tôi chụp lại',25,9),(222,'ADMIN','2024-06-22 14:06:20.776637','ADMIN','2024-06-22 14:06:20.776637','ảnh đây tôi mua hàng áo man united',25,9),(223,'ADMIN','2024-06-22 14:06:31.753749','ADMIN','2024-06-22 14:06:31.753749','vậy áo có vấn đề gì',25,4),(224,'ADMIN','2024-06-22 14:06:47.623729','ADMIN','2024-06-22 14:06:47.623729','tôi muốn hỏi là xem có đổi được size của áo không?',25,9),(225,'ADMIN','2024-06-22 14:07:14.840404','ADMIN','2024-06-22 14:07:14.840404','áo ở trên bạn mua size bao nhiêu',25,4),(226,'ADMIN','2024-06-22 14:07:20.417861','ADMIN','2024-06-22 14:07:20.417861','size XL',25,9),(227,'ADMIN','2024-06-22 14:08:40.488604','ADMIN','2024-06-22 14:08:40.488604','rất tiếc chúng tôi chỉ còn size XL với áo màu đen thôi, nếu bạn thích thì chúng tôi có thể đổi nó cho bạn. Đây là mẫu áo màu đen.',25,4),(228,'ADMIN','2024-06-22 14:09:15.743048','ADMIN','2024-06-22 14:09:15.743048','ok tôi muốn đổi',25,9),(229,'ADMIN','2024-06-22 14:09:49.604519','ADMIN','2024-06-22 14:09:49.604519','bạn đọc cho tôi mã ID order mà bạn đã đặt trong mục đơn hàng của bạn cho tôi để tôi điền thông tin ship',25,4),(230,'ADMIN','2024-06-22 14:10:17.075741','ADMIN','2024-06-22 14:10:17.075741','Mã ID: 35',25,9),(231,'ADMIN','2024-06-22 14:11:03.427037','ADMIN','2024-06-22 14:11:03.427037','ok tôi đã check, đồ sẽ ship tới địa chỉ trên mục đặt hàng mà bạn đã đặt để mua áo màu đỏ trước đó.',25,4),(232,'ADMIN','2024-06-22 14:12:30.099892','ADMIN','2024-06-22 14:12:30.099892','Nếu bạn muốn thay đổi địa chỉ thì liên hệ lại chúng tôi, trong vòng 1 ngày kể từ khi bạn muốn thay đổi đồ từ đỏ sang đen size XL',25,4),(233,'ADMIN','2024-06-22 14:12:34.584076','ADMIN','2024-06-22 14:12:34.584076','ok cảm ơn',25,9),(234,'ADMIN','2024-06-22 14:24:59.278569','ADMIN','2024-06-22 14:24:59.278569','zo',27,9),(235,'ADMIN','2024-06-22 15:06:11.367200','ADMIN','2024-06-22 15:06:11.367200','n',25,4),(236,'ADMIN','2024-06-22 15:06:20.604323','ADMIN','2024-06-22 15:06:20.604323','z',25,9),(237,'ADMIN','2024-06-22 15:06:31.387987','ADMIN','2024-06-22 15:06:31.387987','z',25,4),(238,'ADMIN','2024-06-22 15:06:42.776148','ADMIN','2024-06-22 15:06:42.776148','z',25,4),(239,'ADMIN','2024-06-22 15:10:49.394197','ADMIN','2024-06-22 15:10:49.394197','alo',25,9),(240,'ADMIN','2024-06-22 15:10:52.171352','ADMIN','2024-06-22 15:10:52.171352','gi',25,4),(241,'ADMIN','2024-06-22 15:11:03.824166','ADMIN','2024-06-22 15:11:03.824166','f',25,4),(242,'ADMIN','2024-06-22 15:11:38.813295','ADMIN','2024-06-22 15:11:38.813295','lo',25,9),(243,'ADMIN','2024-06-22 15:14:12.597129','ADMIN','2024-06-22 15:14:12.597129','online khoong',25,4),(244,'ADMIN','2024-06-22 15:14:16.690807','ADMIN','2024-06-22 15:14:16.690807','sao',25,9),(245,'ADMIN','2024-06-22 15:17:23.804926','ADMIN','2024-06-22 15:17:23.804926','s',25,9),(246,'ADMIN','2024-06-22 15:17:28.299138','ADMIN','2024-06-22 15:17:28.299138','',25,4),(247,'ADMIN','2024-06-22 15:17:30.879122','ADMIN','2024-06-22 15:17:30.879122','zz',25,4),(248,'ADMIN','2024-06-22 15:20:51.493460','ADMIN','2024-06-22 15:20:51.493460','zo',25,9),(249,'ADMIN','2024-06-22 16:00:53.809622','ADMIN','2024-06-22 16:00:53.809622','hello',26,9),(250,'ADMIN','2024-06-22 16:00:57.321798','ADMIN','2024-06-22 16:00:57.321798','xin chao',28,9),(251,'ADMIN','2024-06-29 22:34:11.295034','ADMIN','2024-06-29 22:34:11.295034','dgdfg',27,9),(252,'ADMIN','2024-07-01 23:01:31.967430','ADMIN','2024-07-01 23:01:31.967430','xin chao',27,9),(253,'ADMIN','2024-07-01 23:06:25.658969','ADMIN','2024-07-01 23:06:25.658969','alo toi muon doi hang',28,9),(254,'ADMIN','2024-07-01 23:06:31.546708','ADMIN','2024-07-01 23:06:31.546708','glalfa',28,9),(255,'ADMIN','2024-07-01 23:06:35.681889','ADMIN','2024-07-01 23:06:35.681889','al',27,9),(256,'ADMIN','2024-07-01 23:06:42.175485','ADMIN','2024-07-01 23:06:42.175485','alo',25,9);
/*!40000 ALTER TABLE `chat_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_messages_images`
--

DROP TABLE IF EXISTS `chat_messages_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chat_messages_images` (
  `chat_message_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_j4vfuowtwu58c7hlxjewvtwf8` (`images_id`),
  KEY `FKmhahgvc7uihqtnarwac0u4up9` (`chat_message_id`),
  CONSTRAINT `FKk49y734gsm6p9mw0ii62q11ai` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`),
  CONSTRAINT `FKmhahgvc7uihqtnarwac0u4up9` FOREIGN KEY (`chat_message_id`) REFERENCES `chat_messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_messages_images`
--

LOCK TABLES `chat_messages_images` WRITE;
/*!40000 ALTER TABLE `chat_messages_images` DISABLE KEYS */;
INSERT INTO `chat_messages_images` VALUES (191,97),(192,98),(193,99),(195,100),(197,101),(208,102),(210,103),(222,104),(227,105),(252,109),(253,110);
/*!40000 ALTER TABLE `chat_messages_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation_user`
--

DROP TABLE IF EXISTS `conversation_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conversation_user` (
  `conversation_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FKeufyj3g22nd1hy33fet9ul189` (`user_id`),
  KEY `FK1rnwabvvnhpfq6r0ugfgh0iss` (`conversation_id`),
  CONSTRAINT `FK1rnwabvvnhpfq6r0ugfgh0iss` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`),
  CONSTRAINT `FKeufyj3g22nd1hy33fet9ul189` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_user`
--

LOCK TABLES `conversation_user` WRITE;
/*!40000 ALTER TABLE `conversation_user` DISABLE KEYS */;
INSERT INTO `conversation_user` VALUES (1,3),(1,4),(2,4),(2,6),(8,6),(8,3),(19,6),(19,8),(20,4),(20,8),(21,2),(21,3),(22,2),(22,3),(23,2),(23,8),(24,2),(24,8),(25,9),(25,4),(26,9),(26,6),(27,9),(27,3),(28,9),(28,8),(29,10),(29,3);
/*!40000 ALTER TABLE `conversation_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversations`
--

DROP TABLE IF EXISTS `conversations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conversations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversations`
--

LOCK TABLES `conversations` WRITE;
/*!40000 ALTER TABLE `conversations` DISABLE KEYS */;
INSERT INTO `conversations` VALUES (1,NULL,'2024-06-09 15:58:00.000000',NULL,'2024-06-09 15:58:13.000000'),(2,'ADMIN','2024-06-11 15:58:02.903895','ADMIN','2024-06-11 15:58:02.903895'),(8,'ADMIN','2024-06-11 16:58:31.027399','ADMIN','2024-06-11 16:58:31.027399'),(19,'ADMIN','2024-06-11 17:32:21.680185','ADMIN','2024-06-11 17:32:21.680185'),(20,'ADMIN','2024-06-11 17:33:37.477656','ADMIN','2024-06-11 17:33:37.477656'),(21,'ADMIN','2024-06-16 09:48:01.013798','ADMIN','2024-06-16 09:48:01.013798'),(22,'ADMIN','2024-06-16 09:48:09.134930','ADMIN','2024-06-16 09:48:09.134930'),(23,'ADMIN','2024-06-16 09:48:36.343892','ADMIN','2024-06-16 09:48:36.343892'),(24,'ADMIN','2024-06-16 09:49:03.036967','ADMIN','2024-06-16 09:49:03.036967'),(25,'ADMIN','2024-06-17 13:39:20.288972','ADMIN','2024-06-17 13:39:20.288972'),(26,'ADMIN','2024-06-17 13:43:54.625068','ADMIN','2024-06-17 13:43:54.625068'),(27,'ADMIN','2024-06-17 13:44:39.917911','ADMIN','2024-06-17 13:44:39.917911'),(28,'ADMIN','2024-06-19 16:03:25.925248','ADMIN','2024-06-19 16:03:25.925248'),(29,'ADMIN','2024-06-19 16:18:03.097689','ADMIN','2024-06-19 16:18:03.097689');
/*!40000 ALTER TABLE `conversations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupons` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `end` datetime(6) DEFAULT NULL,
  `percent` int(11) DEFAULT NULL,
  `start` datetime(6) DEFAULT NULL,
  `vendor_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vendor_key` (`vendor_id`),
  CONSTRAINT `vendor_key` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (19,'ADMIN','2024-06-12 15:29:09.972868','ADMIN','2024-06-12 15:29:09.972868','2024-06-29 15:28:00.000000',25,'2024-06-12 15:27:00.000000',2,'Khuyến mãi mùa hè','9qriqwakfe');
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `evaluations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` text,
  `rating` tinyint(4) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3mnbfjro4oi3i671mfqng0a6` (`product_id`),
  KEY `FK4prhys5t43v3cce1q09qcljhe` (`user_id`),
  CONSTRAINT `FK4prhys5t43v3cce1q09qcljhe` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKs3mnbfjro4oi3i671mfqng0a6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations`
--

LOCK TABLES `evaluations` WRITE;
/*!40000 ALTER TABLE `evaluations` DISABLE KEYS */;
INSERT INTO `evaluations` VALUES (33,'ADMIN','2024-04-13 09:59:42.439411','ADMIN','2024-04-13 10:06:49.299360','giay rat la dep',5,20,2),(34,'ADMIN','2024-04-13 10:00:32.742913','ADMIN','2024-04-13 10:00:32.742913','test man united',4,7,2),(36,'ADMIN','2024-06-16 07:42:47.774324','ADMIN','2024-06-16 07:42:47.774324','qwwrwr',3,2,4),(42,'ADMIN','2024-06-16 08:43:09.276965','ADMIN','2024-06-16 09:04:56.370917','ao rat la dep',5,7,4),(43,'ADMIN','2024-06-16 09:06:13.395314','ADMIN','2024-06-16 09:06:13.395314','hello',4,5,4),(44,'ADMIN','2024-06-22 14:23:36.417423','ADMIN','2024-06-22 14:23:36.417423','ga mo',5,7,9),(45,'ADMIN','2024-06-22 17:06:43.091136','ADMIN','2024-06-22 17:06:43.091136','ngon nghe',4,3,9),(46,'ADMIN','2024-07-01 23:07:09.553241','ADMIN','2024-07-01 23:07:09.553241','rat dep',5,28,9);
/*!40000 ALTER TABLE `evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations_images`
--

DROP TABLE IF EXISTS `evaluations_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `evaluations_images` (
  `evaluation_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_qyyce4i4d0qrm0iu0oou3oihs` (`images_id`),
  KEY `FKb1txdyqob6yvcjv1ncrcj76fw` (`evaluation_id`),
  CONSTRAINT `FKb1txdyqob6yvcjv1ncrcj76fw` FOREIGN KEY (`evaluation_id`) REFERENCES `evaluations` (`id`),
  CONSTRAINT `FKxnkh6d4kx6b0o87dq09fsg5b` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations_images`
--

LOCK TABLES `evaluations_images` WRITE;
/*!40000 ALTER TABLE `evaluations_images` DISABLE KEYS */;
INSERT INTO `evaluations_images` VALUES (36,74),(42,75),(42,76),(42,77),(43,78),(46,111);
/*!40000 ALTER TABLE `evaluations_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (57,'ADMIN','2024-06-01 10:03:48.903194','ADMIN','2024-06-01 10:03:48.903194','man city mau xanh.jpg','files/image-stock'),(59,'ADMIN','2024-06-02 08:57:14.861646','ADMIN','2024-06-02 08:57:14.861646','ao den.avif','files/image-stock'),(60,'ADMIN','2024-06-02 09:00:55.503224','ADMIN','2024-06-02 09:00:55.503224','ao mu do.avif','files/image-stock'),(61,'ADMIN','2024-06-02 09:13:16.916955','ADMIN','2024-06-02 09:13:16.916955','snearker_den.webp','files/image-stock'),(62,'ADMIN','2024-06-02 09:16:34.570566','ADMIN','2024-06-02 09:16:34.570566','xanhduong.webp','files/image-stock'),(63,'ADMIN','2024-06-02 09:21:00.756136','ADMIN','2024-06-02 09:21:00.756136','liverpol.webp','files/image-stock'),(64,'ADMIN','2024-06-02 09:24:28.558342','ADMIN','2024-06-02 09:24:28.558342','laoptop.webp','files/image-stock'),(65,'ADMIN','2024-06-02 09:26:01.538861','ADMIN','2024-06-02 09:26:01.538861','laptop_trang.webp','files/image-stock'),(66,'ADMIN','2024-06-02 09:28:51.060762','ADMIN','2024-06-02 09:28:51.060762','inter_den.webp','files/image-stock'),(67,'ADMIN','2024-06-02 09:29:51.785712','ADMIN','2024-06-02 09:29:51.785712','argen_xanh.webp','files/image-stock'),(68,'ADMIN','2024-06-11 17:39:00.738978','ADMIN','2024-06-11 17:39:00.738978','the angel next door.jpeg','files/avatar'),(74,'ADMIN','2024-06-16 07:41:50.123955','ADMIN','2024-06-16 07:41:50.123955','the angel next door.jpeg','files/image'),(75,'ADMIN','2024-06-16 09:04:56.333933','ADMIN','2024-06-16 09:04:56.333933','the angel next door.jpeg','files/image'),(76,'ADMIN','2024-06-16 09:04:56.356443','ADMIN','2024-06-16 09:04:56.356443','weather with you.jpg','files/image'),(77,'ADMIN','2024-06-16 09:04:56.360446','ADMIN','2024-06-16 09:04:56.360446','weather_with_you_hina.jpg','files/image'),(78,'ADMIN','2024-06-16 09:06:13.375207','ADMIN','2024-06-16 09:06:13.375207','weather_with_you_hina.jpg','files/image'),(79,'ADMIN','2024-06-16 09:34:19.804582','ADMIN','2024-06-16 09:34:19.804582','do_the_thao.webp','files/image'),(80,'ADMIN','2024-06-16 09:43:36.939110','ADMIN','2024-06-16 09:43:36.939110','may_tinh.jpg','files/image'),(81,'ADMIN','2024-06-16 09:44:23.284594','ADMIN','2024-06-16 09:44:23.284594','cellphone.jpg','files/image'),(82,'ADMIN','2024-06-16 09:45:05.095566','ADMIN','2024-06-16 09:45:05.095566','do-gia-dung-la-gi.jpg','files/image'),(83,'ADMIN','2024-06-16 09:45:43.032608','ADMIN','2024-06-16 09:45:43.032608','giay.jpg','files/image'),(84,'ADMIN','2024-06-16 09:46:15.770554','ADMIN','2024-06-16 09:46:15.770554','do_dung_hoc_tap.jpg','files/image'),(85,'ADMIN','2024-06-19 16:58:29.648757','ADMIN','2024-06-19 16:58:29.648757','mau tim.webp','files/image'),(86,'ADMIN','2024-06-19 17:05:20.656767','ADMIN','2024-06-19 17:05:20.656767','IPHONE WHITE.jpg','files/image'),(87,'ADMIN','2024-06-19 17:06:37.755217','ADMIN','2024-06-19 17:06:37.755217','navi-blue.jpg','files/image'),(88,'ADMIN','2024-06-19 17:10:49.633726','ADMIN','2024-06-19 17:10:49.633726','inox.jpg','files/image'),(89,'ADMIN','2024-06-19 17:12:50.108949','ADMIN','2024-06-19 17:12:50.108949','noi_lau.jpg','files/image'),(90,'ADMIN','2024-06-19 17:16:48.033483','ADMIN','2024-06-19 17:16:48.033483','bep_tu.webp','files/image'),(93,'ADMIN','2024-06-20 16:09:46.095471','ADMIN','2024-06-20 16:09:46.095471','liver_san_khac.jpg','files/image'),(97,'ADMIN','2024-06-21 18:28:55.132530','ADMIN','2024-06-21 18:28:55.132530','weather_with_you_hina.jpg','files/image'),(98,'ADMIN','2024-06-21 18:29:37.856053','ADMIN','2024-06-21 18:29:37.856053','the angel next door.jpeg','files/image'),(99,'ADMIN','2024-06-21 18:30:41.554101','ADMIN','2024-06-21 18:30:41.554101','weather with you.jpg','files/image'),(100,'ADMIN','2024-06-21 18:32:58.356425','ADMIN','2024-06-21 18:32:58.356425','weather with you.jpg','files/image'),(101,'ADMIN','2024-06-21 18:33:30.469580','ADMIN','2024-06-21 18:33:30.469580','weather_with_you_hina.jpg','files/image'),(102,'ADMIN','2024-06-22 13:57:45.116257','ADMIN','2024-06-22 13:57:45.116257','weather with you.jpg','files/image'),(103,'ADMIN','2024-06-22 14:01:02.846627','ADMIN','2024-06-22 14:01:02.846627','the angel next door.jpeg','files/image'),(104,'ADMIN','2024-06-22 14:06:20.771198','ADMIN','2024-06-22 14:06:20.771198','ao mu.jpg','files/image'),(105,'ADMIN','2024-06-22 14:08:40.483598','ADMIN','2024-06-22 14:08:40.483598','ao_mu_den.jpg','files/image'),(106,'ADMIN','2024-06-22 14:50:39.557637','ADMIN','2024-06-22 14:50:39.557637','weather with you.jpg','files/image'),(107,'ADMIN','2024-06-22 16:12:59.471783','ADMIN','2024-06-22 16:12:59.471783','weather_with_you_hina.jpg','files/image'),(108,'ADMIN','2024-07-01 22:53:30.345304','ADMIN','2024-07-01 22:53:30.345304','may_toinh_ban.jpg','files/image'),(109,'ADMIN','2024-07-01 23:01:31.930972','ADMIN','2024-07-01 23:01:31.930972','may_toinh_ban.jpg','files/image'),(110,'ADMIN','2024-07-01 23:06:25.625392','ADMIN','2024-07-01 23:06:25.625392','may_toinh_ban.jpg','files/image'),(111,'ADMIN','2024-07-01 23:07:09.510972','ADMIN','2024-07-01 23:07:09.510972','may_toinh_ban.jpg','files/image');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `languages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `name_vn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,NULL,NULL,NULL,NULL,'ao liverpool','Áo liverpool'),(2,NULL,NULL,NULL,NULL,'laptop lenovo thinkpad','laptop lenovo thinkpad'),(3,NULL,NULL,NULL,NULL,'ao mancity','Áo mancity'),(4,'ADMIN','2024-03-19 21:17:39.902424','ADMIN','2024-03-19 21:17:39.902424','ao messi inter miami','Áo Inter Miami'),(5,'ADMIN','2024-03-19 21:22:07.768176','ADMIN','2024-03-19 21:22:07.768176','ao argentina messi, ao messi, ao ms','Áo Argentina'),(6,'ADMIN','2024-03-20 19:52:57.302841','ADMIN','2024-03-20 19:52:57.302841','ao mancity chinh hang, ao mc, mc, mc chinh hang, ao mc chinh hang','Áo mancity chính hãng'),(7,'ADMIN','2024-03-20 19:55:23.740519','ADMIN','2024-03-20 19:55:23.740519','ao man manchester united chinh hang, ao mu chinh hang, ao man united chinh hang','Áo Manchester United Chính Hãng'),(19,'ADMIN','2024-04-04 10:56:40.216671','ADMIN','2024-04-04 10:56:40.216671','te','tes'),(20,'ADMIN','2024-04-04 10:57:53.960208','ADMIN','2024-04-04 10:57:53.960208','giay sneaker de trang','Giày sneaker đế trắng'),(24,'ADMIN','2024-06-19 16:54:08.578425','ADMIN','2024-06-19 16:54:08.578425','iphone 15 promax','Iphone 15 ProMax'),(26,'ADMIN','2024-06-19 17:08:58.226967','ADMIN','2024-06-19 17:08:58.226967','noi lau, bep lau, bep tu','Nồi lẩu'),(27,'ADMIN','2024-06-19 17:16:08.852020','ADMIN','2024-06-19 17:16:08.852020','bep tu','Bếp từ'),(28,'ADMIN','2024-06-29 22:44:08.399003','ADMIN','2024-06-29 22:44:08.399003','may tinh ban','Máy tính bàn');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKix2l63ilcu384ke5kprwx1xy` (`product_id`),
  CONSTRAINT `FKix2l63ilcu384ke5kprwx1xy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'ADMIN','2024-06-29 22:44:31.890209','ADMIN','2024-06-29 22:44:31.890209','shop quang phu vừa có sản phẩm mới, có lẽ bạn sẽ thích: Máy tính bàn',28),(2,'ADMIN','2024-07-01 22:53:30.438607','ADMIN','2024-07-01 22:53:30.438607','Sản phẩm: Máy tính bàn vừa có thêm loại mặt hàng mới vui lòng kiểm tra',28);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `approval` bit(1) NOT NULL,
  `payment` enum('PAY_AT_HOME','PAY_BY_BANK') DEFAULT NULL,
  `purchased` bit(1) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `received` bit(1) NOT NULL,
  `status` enum('ALL','SUCCESS','PROCESSING','NOT_APPROVAL') DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `stock_id` bigint(20) DEFAULT NULL,
  `stock_classification_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1d1gkxckw648m2n2d5gx0yx5` (`coupon_id`),
  KEY `FK4jg39ja0rjvjyydibgtbp8b6b` (`stock_id`),
  KEY `FKrl8gel05npxsgjr9fhgnuy450` (`stock_classification_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK4jg39ja0rjvjyydibgtbp8b6b` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`),
  CONSTRAINT `FKn1d1gkxckw648m2n2d5gx0yx5` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`),
  CONSTRAINT `FKrl8gel05npxsgjr9fhgnuy450` FOREIGN KEY (`stock_classification_id`) REFERENCES `stock_classifications` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'ADMIN','2024-06-22 16:15:36.100172','ADMIN','2024-06-22 16:15:36.100172',_binary '','PAY_BY_BANK',_binary '\0',1,_binary '\0','PROCESSING',NULL,27,29,9),(3,'ADMIN','2024-06-22 16:40:14.260640','ADMIN','2024-07-01 23:06:03.608112',_binary '','PAY_BY_BANK',_binary '',1,_binary '\0','PROCESSING',NULL,22,40,9),(4,'ADMIN','2024-06-22 17:00:44.188834','ADMIN','2024-06-22 17:00:44.188834',_binary '','PAY_BY_BANK',_binary '\0',1,_binary '\0','PROCESSING',NULL,22,40,4),(5,'ADMIN','2024-07-01 23:05:25.488254','ADMIN','2024-07-01 23:05:25.488254',_binary '\0','PAY_BY_BANK',_binary '\0',2,_binary '\0','NOT_APPROVAL',NULL,20,14,9);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `description` text,
  `category_id` bigint(20) NOT NULL,
  `vendor_id` bigint(20) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_erc660ddddcmotkvbb85mhumo` (`language_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKs6kdu75k7ub4s95ydsr52p59s` (`vendor_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKs6kdu75k7ub4s95ydsr52p59s` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`),
  CONSTRAINT `FKtfnro803vqrg0ap1a1pmg0tn3` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'ADMIN','2024-03-16 08:23:54.924630','ADMIN','2024-03-16 08:23:54.924630','ÁO LIVERPOOL',1,1,1),(3,'ADMIN','2024-03-16 10:01:49.789719','ADMIN','2024-03-16 10:01:49.789719','RAM: 16GB LPDDR3 2133MHz Onboard\r\nỔ cứng: SSD 512GB M.2 PCIe NVMe\r\nMàn hình: 14.0\" FHD IPS, anti-glare, low power, 400 nits\r\nCPU: i7 10610U Processor with vPro™ (1.80 - 4.90 GHz, 4 Cores, 8 Threads, 8 MB Cache)\r\nVGA: Intel UHD Graphics\r\nPin: 51Wh',2,1,2),(4,'ADMIN','2024-03-19 21:17:39.895418','ADMIN','2024-03-19 21:17:39.895418','áo messi inter',1,1,4),(5,'ADMIN','2024-03-19 21:22:07.765155','ADMIN','2024-03-19 21:22:07.765155','ao argentina messi, Áo argentina Messi ao messi',1,1,5),(6,'ADMIN','2024-03-20 19:52:57.299333','ADMIN','2024-03-20 19:52:57.299333','Áo mancity chính hãng',1,2,6),(7,'ADMIN','2024-03-20 19:55:23.740519','ADMIN','2024-03-20 19:55:23.740519','Áo Manchester United Chính Hãng',1,2,7),(20,'ADMIN','2024-04-04 10:57:53.960208','ADMIN','2024-04-04 10:57:53.960208','Giày sneaker đế trắng',5,2,20),(24,'ADMIN','2024-06-19 16:54:08.573424','ADMIN','2024-06-19 16:54:08.573424','Capacity:\n128GB; 256GB; 512GB; 128GB; 256GB; 512GB\nThe iPhone 15 display has rounded corners that follow a beautiful curved design, and these corners are within a standard rectangle. When measured as a standard rectangular shape, the screen is 6.12 inches diagonally (actual viewable area is less).\nBoth models\nDynamic Island\nHDR display\nTrue Tone\nWide color (P3)\nHaptic Touch\n2,000,000:1 contrast ratio (typical)\n1000 nits max brightness (typical); 1600 nits peak brightness (HDR); 2000 nits peak brightness (outdoor)\nFingerprint-resistant oleophobic coating\nSupport for display of multiple languages and characters simultaneously',3,5,24),(26,'ADMIN','2024-06-19 17:08:58.222965','ADMIN','2024-06-19 17:08:58.222965','Nồi lẩu chất lượng cao',4,5,26),(27,'ADMIN','2024-06-19 17:16:08.852020','ADMIN','2024-06-19 17:16:08.852020','Bếp từ chất lượng cao',4,5,27),(28,'ADMIN','2024-06-29 22:44:08.363955','ADMIN','2024-06-29 22:44:08.363955','ASUS S500TE i3 13100 (S500TE-313100037W) là mẫu máy tính để bàn hiện đại đến từ thương hiệu ASUS, mang đến cho bạn giải pháp làm việc hoàn hảo. Thiết kế tối giản đẹp mắt cùng hiệu năng ổn định, chiếc PC này sẽ đáp ứng mọi nhu cầu công việc của bạn một cách mượt mà và hiệu quả.\n• Được trang bị bộ vi xử lý Intel Core i3 13100 mạnh mẽ với tốc độ xung nhịp tối đa lên đến 4.50 GHz, máy tính để bàn Asus mang đến cho bạn hiệu năng vượt trội, xử lý mượt mà các tác vụ văn phòng như Word, Excel, PowerPoint,...\n\n• Bộ nhớ RAM 8 GB của máy tính để bàn cho phép bạn thực hiện đa nhiệm một cách hiệu quả, mở nhiều ứng dụng và cửa sổ làm việc cùng lúc mà vẫn đảm bảo độ mượt mà khi chuyển đổi qua lại. Với nhu cầu sử dụng cao hơn, bạn hoàn toàn có thể dễ dàng nâng cấp RAM lên đến 64 GB, đáp ứng mọi yêu cầu công việc và giải trí của bạn.\n\n• Ổ cứng SSD 256 GB trên chiếc máy tính để bàn này mang đến cho bạn trải nghiệm sử dụng vượt trội. Khởi động máy và ứng dụng chỉ trong vài giây, tiết kiệm thời gian chờ đợi đáng kể. Đồng thời, tạo một không gian lưu trữ vừa để bạn cất trữ các loại tệp tin, từ video, hình ảnh, văn bản,... đến các ứng dụng nặng.\n\n• PC sở hữu tông màu đen trung tính đầy hiện đại, mang đến vẻ đẹp tinh tế cho không gian làm việc của bạn. Phần vỏ bên ngoài được làm dạng nhám và xước vô cùng độc đáo, tạo điểm nhấn cho không gian làm việc của bạn. Với khối lượng tổng thể chỉ 7.7 kg dễ dàng đặt trực tiếp lên bàn làm việc mà không chiếm nhiều diện tích. \n\n• Điểm cộng của sản phẩm này là hãng đã trang bị kèm theo cả chuột và bàn phím giúp bạn tiết kiệm chi phí và không tốn thời gian tìm mua thêm phụ kiện.\n\n• Máy tính để bàn học tập - văn phòng được trang bị khá nhiều cổng giao tiếp ở cả 2 mặt trước và sau như: DisplayPort 1.4, HDMI 1.4, VGA, USB 3.2, USB 2.0,... dễ dàng kết nối với các thiết bị ngoại vi khác.',2,1,28);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_classifications`
--

DROP TABLE IF EXISTS `stock_classifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stock_classifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `quantity_of_product` int(11) DEFAULT NULL,
  `size` enum('NO_SIZE','S','M','L','XL','XXL','N_39','N_40','N_41','N_42','N_43','N_44','N_45') DEFAULT NULL,
  `seller` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_classifications`
--

LOCK TABLES `stock_classifications` WRITE;
/*!40000 ALTER TABLE `stock_classifications` DISABLE KEYS */;
INSERT INTO `stock_classifications` VALUES (4,'ADMIN','2024-06-20 11:46:30.903829','ADMIN','2024-06-20 11:46:30.903829',0,'S',0),(5,'ADMIN','2024-06-20 11:46:30.988884','ADMIN','2024-06-20 11:46:30.988884',56,'M',0),(6,'ADMIN','2024-06-20 11:46:30.996835','ADMIN','2024-06-20 11:46:30.996835',80,'L',0),(7,'ADMIN','2024-06-20 15:54:01.003430','ADMIN','2024-06-20 15:54:01.003430',49,'N_40',0),(8,'ADMIN','2024-06-20 15:54:01.034668','ADMIN','2024-06-20 15:54:01.034668',70,'N_41',0),(9,'ADMIN','2024-06-20 15:54:01.035679','ADMIN','2024-06-20 15:54:01.035679',100,'N_42',0),(10,'ADMIN','2024-06-20 15:54:01.041213','ADMIN','2024-06-20 15:54:01.041213',75,'N_43',0),(11,'ADMIN','2024-06-20 16:00:44.171873','ADMIN','2024-06-20 16:00:44.171873',49,'N_44',0),(12,'ADMIN','2024-06-20 16:00:44.177881','ADMIN','2024-06-20 16:00:44.177881',20,'N_42',0),(13,'ADMIN','2024-06-20 16:00:44.179881','ADMIN','2024-06-20 16:00:44.179881',66,'N_43',0),(14,'ADMIN','2024-06-20 16:03:18.997580','ADMIN','2024-06-21 16:00:45.956731',66,'M',2),(15,'ADMIN','2024-06-20 16:03:19.001582','ADMIN','2024-06-20 16:03:19.001582',56,'L',0),(16,'ADMIN','2024-06-20 16:03:19.005094','ADMIN','2024-06-20 16:03:19.005094',80,'XL',0),(17,'ADMIN','2024-06-20 16:04:32.683907','ADMIN','2024-06-20 16:04:32.683907',66,'M',0),(18,'ADMIN','2024-06-20 16:04:32.687271','ADMIN','2024-06-20 16:04:32.687271',20,'S',0),(19,'ADMIN','2024-06-20 16:04:32.688288','ADMIN','2024-06-20 16:04:32.688288',120,'XL',0),(20,'ADMIN','2024-06-20 16:06:32.808392','ADMIN','2024-06-20 16:06:32.808392',66,'M',0),(21,'ADMIN','2024-06-20 16:06:32.829392','ADMIN','2024-06-20 16:06:32.829392',20,'S',0),(22,'ADMIN','2024-06-20 16:06:32.831455','ADMIN','2024-06-20 16:06:32.831455',120,'XL',0),(23,'ADMIN','2024-06-20 16:09:46.125136','ADMIN','2024-06-20 16:09:46.125136',66,'M',0),(24,'ADMIN','2024-06-20 16:09:46.128138','ADMIN','2024-06-20 16:09:46.128138',20,'S',0),(25,'ADMIN','2024-06-20 16:09:46.130137','ADMIN','2024-06-20 16:09:46.130137',120,'XL',0),(26,'ADMIN','2024-06-20 16:13:58.892571','ADMIN','2024-06-20 16:13:58.892571',66,'M',0),(27,'ADMIN','2024-06-20 16:13:58.895571','ADMIN','2024-06-20 16:13:58.895571',56,'L',0),(28,'ADMIN','2024-06-20 16:13:58.896582','ADMIN','2024-06-20 16:13:58.896582',80,'XL',0),(29,'ADMIN','2024-06-20 16:15:01.110513','ADMIN','2024-06-20 16:15:01.110513',66,'M',0),(30,'ADMIN','2024-06-20 16:15:01.113735','ADMIN','2024-06-20 16:15:01.113735',56,'L',0),(31,'ADMIN','2024-06-20 16:15:01.117363','ADMIN','2024-06-20 16:15:01.117363',80,'XL',0),(32,'ADMIN','2024-06-20 16:16:16.967297','ADMIN','2024-06-20 16:16:16.967297',66,'M',0),(33,'ADMIN','2024-06-20 16:16:16.969258','ADMIN','2024-06-20 16:16:16.969258',56,'L',0),(34,'ADMIN','2024-06-20 16:16:16.970327','ADMIN','2024-06-20 16:16:16.970327',80,'XL',0),(35,'ADMIN','2024-06-20 16:16:39.777612','ADMIN','2024-06-22 16:06:36.848834',120,'S',11),(36,'ADMIN','2024-06-20 16:16:39.780600','ADMIN','2024-06-20 16:16:39.780600',56,'L',0),(37,'ADMIN','2024-06-20 16:16:39.783600','ADMIN','2024-06-22 14:23:09.653129',50,'XL',20),(40,NULL,NULL,'ADMIN','2024-07-01 23:06:03.608112',66,'NO_SIZE',1),(41,NULL,NULL,NULL,NULL,44,'NO_SIZE',0),(42,'ADMIN','2024-06-22 17:08:02.531849','ADMIN','2024-06-22 17:08:02.531849',99,'NO_SIZE',0),(43,'ADMIN','2024-06-22 17:08:46.033724','ADMIN','2024-06-22 17:08:46.033724',120,'NO_SIZE',0),(44,'ADMIN','2024-06-22 17:10:17.029138','ADMIN','2024-06-22 17:10:17.029138',555,'NO_SIZE',0),(45,'ADMIN','2024-06-22 17:10:46.468916','ADMIN','2024-06-22 17:10:46.468916',1220,'NO_SIZE',0),(46,'ADMIN','2024-06-22 17:11:12.509383','ADMIN','2024-06-22 17:11:12.509383',5000,'NO_SIZE',0),(47,'ADMIN','2024-06-22 17:11:32.168869','ADMIN','2024-06-22 17:11:32.168869',13142,'NO_SIZE',0),(48,NULL,NULL,NULL,NULL,200,'NO_SIZE',0),(49,'ADMIN','2024-07-01 22:53:30.430598','ADMIN','2024-07-01 22:53:30.430598',200,'NO_SIZE',0);
/*!40000 ALTER TABLE `stock_classifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stocks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `color` enum('NO_COLOR','RED','WHITE','BLUE','GREEN','YELLOW','BLACK','PINK','VIOLET') DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKff7be959jyco0iukc1dcjj9qm` (`product_id`),
  CONSTRAINT `FKff7be959jyco0iukc1dcjj9qm` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (4,NULL,NULL,'ADMIN','2024-06-20 11:46:32.085513','man_city_blue',6,'BLUE',500000),(10,'ADMIN','2024-06-02 08:57:14.927435','ADMIN','2024-06-02 08:57:14.927435','man_united_black',7,'BLACK',500000),(13,'ADMIN','2024-06-02 09:01:13.967246','ADMIN','2024-06-20 16:16:39.788139','man_united_red',7,'RED',700000),(14,'ADMIN','2024-06-02 09:13:16.926226','ADMIN','2024-06-02 09:13:16.926226','sneaker_white',20,'WHITE',500000),(19,'ADMIN','2024-06-02 09:16:44.682576','ADMIN','2024-06-20 16:00:44.194158','sneaker_blue',20,'BLUE',560000),(20,'ADMIN','2024-06-02 09:21:00.771893','ADMIN','2024-06-20 16:03:19.010094','liverpool_red',2,'RED',700000),(21,NULL,NULL,'ADMIN','2024-06-20 16:09:46.138655','liverpool_xanh_la',2,'GREEN',550000),(22,'ADMIN','2024-06-02 09:24:28.580344','ADMIN','2024-06-02 09:24:28.580344','laptop_black',3,'BLACK',500000),(23,'ADMIN','2024-06-02 09:26:01.552856','ADMIN','2024-06-02 09:26:01.552856','laptop_white',3,'WHITE',500000),(24,'ADMIN','2024-06-02 09:28:51.068762','ADMIN','2024-06-02 09:28:51.068762','inter_miami',4,'BLACK',500000),(27,'ADMIN','2024-06-02 09:30:07.463926','ADMIN','2024-06-20 16:15:01.124349','argentina_blue',5,'BLUE',500000),(28,'ADMIN','2024-06-19 16:58:29.660033','ADMIN','2024-06-22 17:08:02.539712','iphone_15_tim',24,'VIOLET',38000000),(29,'ADMIN','2024-06-19 17:05:20.728707','ADMIN','2024-06-22 17:08:46.038882','iphone_15_white',24,'WHITE',36500000),(30,'ADMIN','2024-06-19 17:06:37.762631','ADMIN','2024-06-22 17:10:17.038683','iphone_15_blue',24,'BLUE',37500000),(31,'ADMIN','2024-06-19 17:10:49.639726','ADMIN','2024-06-22 17:11:32.178232','noi_lau_bep_tu',26,'WHITE',250000),(32,'ADMIN','2024-06-19 17:12:50.122607','ADMIN','2024-06-22 17:11:12.517359','noi_lau_bep_tu_hong_trang',26,'PINK',400000),(33,'ADMIN','2024-06-19 17:16:48.044583','ADMIN','2024-06-19 17:16:48.044583','bep_tu_den',27,'BLACK',500000),(34,NULL,NULL,NULL,NULL,'may_tinh_ban_den',28,'BLACK',15000000);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks_images`
--

DROP TABLE IF EXISTS `stocks_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stocks_images` (
  `stock_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  KEY `FK3bqpbibpgls3jmdvipl2s9ore` (`stock_id`),
  KEY `stocks_images_images_id_fk` (`images_id`),
  CONSTRAINT `FK3bqpbibpgls3jmdvipl2s9ore` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`),
  CONSTRAINT `stocks_images_images_id_fk` FOREIGN KEY (`images_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks_images`
--

LOCK TABLES `stocks_images` WRITE;
/*!40000 ALTER TABLE `stocks_images` DISABLE KEYS */;
INSERT INTO `stocks_images` VALUES (10,59),(13,60),(14,61),(19,62),(20,63),(22,64),(23,65),(24,66),(27,67),(28,85),(29,86),(30,87),(31,88),(32,89),(33,90),(4,57),(21,93),(34,108);
/*!40000 ALTER TABLE `stocks_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks_stock_classifications`
--

DROP TABLE IF EXISTS `stocks_stock_classifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stocks_stock_classifications` (
  `stock_id` bigint(20) NOT NULL,
  `stock_classifications_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_a0ofrj6xxvxvavw1neao5ry77` (`stock_classifications_id`),
  KEY `FKjrdwqnmbg8hro8up8jdcqkfsa` (`stock_id`),
  CONSTRAINT `FKjrdwqnmbg8hro8up8jdcqkfsa` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`),
  CONSTRAINT `FKplmtwnhg1s3wglry0q00te1f2` FOREIGN KEY (`stock_classifications_id`) REFERENCES `stock_classifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks_stock_classifications`
--

LOCK TABLES `stocks_stock_classifications` WRITE;
/*!40000 ALTER TABLE `stocks_stock_classifications` DISABLE KEYS */;
INSERT INTO `stocks_stock_classifications` VALUES (4,4),(4,5),(4,6),(10,32),(10,33),(10,34),(13,35),(13,36),(13,37),(14,7),(14,8),(14,9),(14,10),(19,11),(19,12),(19,13),(20,14),(20,15),(20,16),(21,23),(21,24),(21,25),(22,40),(23,41),(24,26),(24,27),(24,28),(27,29),(27,30),(27,31),(28,42),(29,43),(30,44),(31,47),(32,46),(33,45),(34,49);
/*!40000 ALTER TABLE `stocks_stock_classifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','VENDOR','ADMIN') DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `image_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_94dj9ry3k3tmcsyg8eatp7vvn` (`image_id`),
  CONSTRAINT `FK17herqt2to4hyl5q5r5ogbxk9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,NULL,'2024-03-15 09:42:23.093861','ADMIN','2024-05-19 10:49:49.730819','quangphu2060@gmail.com','$2a$10$4WepOirdpJfhsSXXmlQEJ.h3SOlZndGwLX/Ar1sClK9O76hkVKvbW','ADMIN','bac ninh phu luu',NULL,'Phú Nguyễn Quang','0982370967','BAC NINH',NULL,'irohas2004',NULL),(3,NULL,'2024-03-19 19:33:05.659325','ADMIN','2024-06-17 17:16:26.810438','quangphu2060@gmail.com','$2a$10$4WepOirdpJfhsSXXmlQEJ.h3SOlZndGwLX/Ar1sClK9O76hkVKvbW','VENDOR','121',NULL,'Tran Nguyen Quang Nam','2121212','2121212',NULL,'quangphu',NULL),(4,NULL,'2024-03-19 21:33:51.674436','ADMIN','2024-06-15 11:21:26.698586','nguyenquangphu05022004@gmail.com','$2a$10$jUth3cSIoUnBCW37xf7qKutsiQDxnRDMU5X7DMBcYPyxA4TNlZywa','VENDOR','yen phong bac ninh',NULL,'quang phu','024095023523','bac ninh',NULL,'user',NULL),(6,NULL,'2024-03-28 16:36:00.463221','ADMIN','2024-04-07 11:51:21.139126','quangphu2060@gmail.com','$2a$10$4WepOirdpJfhsSXXmlQEJ.h3SOlZndGwLX/Ar1sClK9O76hkVKvbW','VENDOR','Phu Luu Trung Nghia Yen Phong Bac Ninh',NULL,'Nguyễn Quang Phú','0982370967',NULL,NULL,'studytonight',NULL),(8,NULL,NULL,'ADMIN','2024-06-21 13:01:44.847029','quangphu2050@gmail.com','$2a$10$tA7TZSE5VqhIIsbTyIxKPOk9Ji..ImTk7iCUXAK7EOKVVTbqyLXZ.','VENDOR','phu luu',NULL,'quang phu','21402425','bac ninh',NULL,'B22DCCN621',68),(9,NULL,'2024-06-17 13:36:07.047472','ADMIN','2024-07-01 23:05:25.551280','test@gmail.com','$2a$10$t4s51AjI12ES2WhpQ2XyuO33yPIXAlAktL5pr8rHa97ncTTyHtN4y','USER','werwrwrwer',NULL,'test1','test','werwer',NULL,'test',107),(10,NULL,'2024-06-19 16:17:50.758073',NULL,'2024-06-19 16:17:50.758073','test2@gmail.com','$2a$10$stCFE0l3xjVlRtLKOchcleZs5L6Zf6A4xcwVpuzMc9sVpcVCbDrZq','USER',NULL,NULL,NULL,NULL,NULL,NULL,'test2',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_notifications`
--

DROP TABLE IF EXISTS `users_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users_notifications` (
  `user_id` bigint(20) NOT NULL,
  `notifications_id` bigint(20) NOT NULL,
  KEY `FKil3tssmpyic5ruavb9jbbw2bb` (`user_id`),
  KEY `users_notifications_notifications_id_fk` (`notifications_id`),
  CONSTRAINT `FKil3tssmpyic5ruavb9jbbw2bb` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `users_notifications_notifications_id_fk` FOREIGN KEY (`notifications_id`) REFERENCES `notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_notifications`
--

LOCK TABLES `users_notifications` WRITE;
/*!40000 ALTER TABLE `users_notifications` DISABLE KEYS */;
INSERT INTO `users_notifications` VALUES (9,1),(9,2);
/*!40000 ALTER TABLE `users_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor_favorites`
--

DROP TABLE IF EXISTS `vendor_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendor_favorites` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor_favorites`
--

LOCK TABLES `vendor_favorites` WRITE;
/*!40000 ALTER TABLE `vendor_favorites` DISABLE KEYS */;
INSERT INTO `vendor_favorites` VALUES (1,'ADMIN','2024-06-29 21:18:55.525716','ADMIN','2024-06-29 21:18:55.525716'),(3,NULL,NULL,NULL,NULL),(4,'ADMIN','2024-06-30 16:33:09.760017','ADMIN','2024-06-30 16:33:09.760017');
/*!40000 ALTER TABLE `vendor_favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor_favorites_users`
--

DROP TABLE IF EXISTS `vendor_favorites_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendor_favorites_users` (
  `vendor_favorite_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  KEY `vendor_favorites_users_users_id_fk` (`users_id`),
  KEY `FK81m5sy9l3av752rudwuqyy49e` (`vendor_favorite_id`),
  CONSTRAINT `FK81m5sy9l3av752rudwuqyy49e` FOREIGN KEY (`vendor_favorite_id`) REFERENCES `vendor_favorites` (`id`),
  CONSTRAINT `vendor_favorites_users_users_id_fk` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor_favorites_users`
--

LOCK TABLES `vendor_favorites_users` WRITE;
/*!40000 ALTER TABLE `vendor_favorites_users` DISABLE KEYS */;
INSERT INTO `vendor_favorites_users` VALUES (1,9),(3,9),(4,9);
/*!40000 ALTER TABLE `vendor_favorites_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `per_money_delivery` int(11) DEFAULT NULL,
  `shop_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `vendor_favorite_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i4fsfxe266yl9oye02ongwl8` (`user_id`),
  KEY `FKbuph4d5yn4q3bb5buwpufklx7` (`vendor_favorite_id`),
  CONSTRAINT `FKbuph4d5yn4q3bb5buwpufklx7` FOREIGN KEY (`vendor_favorite_id`) REFERENCES `vendor_favorites` (`id`),
  CONSTRAINT `FKiuqso7j7nivq7sb3v3v4j7ein` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (1,'ADMIN','2024-03-19 19:43:13.532083','ADMIN','2024-06-29 21:18:55.716446',15000,'shop quang phu',3,1),(2,'ADMIN','2024-03-20 19:51:35.221903','ADMIN','2024-03-20 19:51:35.221903',15000,'kimdung',4,3),(3,'ADMIN','2024-04-07 11:51:20.966430','ADMIN','2024-04-07 11:51:20.966430',200000,'study-night',6,NULL),(5,NULL,NULL,'ADMIN','2024-06-30 16:33:09.765117',20000,'VN-SHOP',8,4);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verify`
--

DROP TABLE IF EXISTS `verify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `verify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `code` varchar(12) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_trg61pfj9e6nx1yc39we3f8ox` (`code`),
  UNIQUE KEY `UK_eoloox20buv32h1yqgvwls3q8` (`user_id`),
  CONSTRAINT `FKnf9v9on2xkg70ky8hnaijt1a4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verify`
--

LOCK TABLES `verify` WRITE;
/*!40000 ALTER TABLE `verify` DISABLE KEYS */;
INSERT INTO `verify` VALUES (1,NULL,'2024-03-24 15:04:54.038874',NULL,'2024-05-19 10:49:49.742836','000562068954',2),(2,NULL,'2024-06-12 15:22:01.585152',NULL,'2024-06-12 15:55:53.659471','595467330717',8),(3,NULL,'2024-06-22 14:57:06.666146',NULL,'2024-06-22 14:57:06.666146','861970755877',9);
/*!40000 ALTER TABLE `verify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ecommerce'
--

--
-- Dumping routines for database 'ecommerce'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10 15:28:37
