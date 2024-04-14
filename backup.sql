-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpsbpg5lc7uk06uixvl9ehdkvt` (`product_id`),
  KEY `FK810a8gq30miyp6j1eub97qm6k` (`user_id`),
  CONSTRAINT `FK810a8gq30miyp6j1eub97qm6k` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpsbpg5lc7uk06uixvl9ehdkvt` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (1,'ADMIN','2024-03-30 13:18:18.817463','ADMIN','2024-03-30 13:23:34.161795',6,4,1),(2,'ADMIN','2024-03-30 13:23:50.040356','ADMIN','2024-04-01 17:51:41.017153',8,4,2),(4,'ADMIN','2024-03-31 17:09:32.984240','ADMIN','2024-03-31 17:09:32.984240',1,5,4);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` enum('SUCCESS','PROCESSING','CANCEL') DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_575bqi2iwedxvaiwo0mbg60cp` (`order_id`),
  CONSTRAINT `FK2s1iwv6bgsmh8u9awhdd1aela` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (1,'ADMIN','2024-03-31 16:00:35.362636','ADMIN','2024-04-04 16:13:37.862500','Hóa đơn: Áo Manchester United Chính Hãng','SUCCESS',2),(2,'ADMIN','2024-04-04 15:52:08.970661','ADMIN','2024-04-04 15:52:08.970661','Hóa đơn: Áo liverpool','PROCESSING',3),(3,'ADMIN','2024-04-09 19:06:05.008372','ADMIN','2024-04-09 19:06:05.008372','Hóa đơn: Áo Messi Inter Miami','PROCESSING',4),(4,'ADMIN','2024-04-09 19:41:58.721272','ADMIN','2024-04-09 19:41:58.721272','Hóa đơn: laptop lenovo thinkpad','PROCESSING',5),(5,'ADMIN','2024-04-09 19:43:13.603459','ADMIN','2024-04-09 19:43:13.603459','Hóa đơn: Áo argentina Messi','PROCESSING',6);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TR_AfterUpdate` AFTER UPDATE ON `bills` FOR EACH ROW begin
        if NEW.status = 'SUCCESS' THEN
            update track_product_sellers
            set track_product_sellers.number_of_products_sold = track_product_sellers.number_of_products_sold + 1
            where track_product_sellers.product_id = (
                select
                    o.product_id
                from bills b inner join orders o on b.order_id = o.id
                where b.id  = NEW.id
            );
        end if;

    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `image_id` bigint DEFAULT NULL,
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
INSERT INTO `categories` VALUES (1,'ADMIN','2024-03-14 18:58:46.871492','ADMIN','2024-04-03 17:29:52.192485','Đồ thể thao',12),(2,'ADMIN','2024-03-16 09:59:18.775572','ADMIN','2024-04-03 17:30:24.664926','Máy tính',13),(3,'ADMIN','2024-03-28 22:54:44.024534','ADMIN','2024-04-03 17:30:54.958466','Điện thoại',14),(4,'ADMIN','2024-04-03 16:39:50.408472','ADMIN','2024-04-03 16:39:50.408472','Đồ gia dụng',9),(5,'ADMIN','2024-04-03 16:57:38.554314','ADMIN','2024-04-03 16:57:38.554314','Giày',10),(6,'ADMIN','2024-04-04 09:01:55.715264','ADMIN','2024-04-04 09:01:55.715264','Đồ dùng học tập',15);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `end` datetime(6) DEFAULT NULL,
  `percent` int DEFAULT NULL,
  `start` datetime(6) DEFAULT NULL,
  `vendor_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `expired` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s5sgl862xn7c0cihpdefd67pm` (`vendor_id`),
  CONSTRAINT `FKeo53jdsgc5htpc5wuiyd4vkt6` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (1,'ADMIN','2024-04-07 10:33:08.896002','ADMIN','2024-04-07 10:33:08.896002','2024-04-30 00:00:00.000000',20,'2024-04-28 00:00:00.000000',2,'Khuyến mãi sự kiện 30/4 - 01/05','846u4jgr3',_binary '\0'),(3,'ADMIN','2024-04-07 11:58:56.745754','ADMIN','2024-04-07 11:58:56.745754','2024-05-01 11:00:00.000000',15,'2024-04-29 11:00:00.000000',1,'Khuyến mãi 30/04 - 01/05','9tejgkwg9',_binary '\0');
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` text,
  `number_of_like` int DEFAULT NULL,
  `rating` tinyint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3mnbfjro4oi3i671mfqng0a6` (`product_id`),
  KEY `FK4prhys5t43v3cce1q09qcljhe` (`user_id`),
  CONSTRAINT `FK4prhys5t43v3cce1q09qcljhe` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKs3mnbfjro4oi3i671mfqng0a6` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations`
--

LOCK TABLES `evaluations` WRITE;
/*!40000 ALTER TABLE `evaluations` DISABLE KEYS */;
INSERT INTO `evaluations` VALUES (23,'ADMIN','2024-04-04 16:36:58.515631','ADMIN','2024-04-04 16:52:42.317875','te',0,3,7,2);
/*!40000 ALTER TABLE `evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_url` varchar(255) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKghwsjbjo7mg3iufxruvq6iu3q` (`product_id`),
  CONSTRAINT `FKghwsjbjo7mg3iufxruvq6iu3q` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (3,'ADMIN','2024-03-23 09:41:40.400208','ADMIN','2024-03-23 09:41:40.400208','weather with you.jpg','files/avatar',NULL),(4,'ADMIN','2024-03-23 09:45:51.234351','ADMIN','2024-03-23 09:45:51.234351','the angel next door.jpeg','files/avatar',NULL),(5,'ADMIN','2024-03-28 16:41:45.724235','ADMIN','2024-03-28 16:41:45.724235','weather with you.jpg','files/avatar',NULL),(6,'ADMIN','2024-03-28 22:51:53.640234','ADMIN','2024-03-28 22:51:53.640234','inbound1392300809626426533.jpg','files/avatar',NULL),(8,'ADMIN','2024-03-29 07:11:45.339891','ADMIN','2024-03-29 07:11:45.339891','the angel next door.jpeg','files/avatar',NULL),(9,'ADMIN','2024-04-03 16:39:49.667670','ADMIN','2024-04-03 16:39:49.667670','do_gia_dung.jpg','files/image-category',NULL),(10,'ADMIN','2024-04-03 16:57:38.451754','ADMIN','2024-04-03 16:57:38.451754','sho.webp','files/image-category',NULL),(12,'ADMIN','2024-04-03 17:29:52.159508','ADMIN','2024-04-03 17:29:52.159508','sport.jpg','files/image-category',NULL),(13,'ADMIN','2024-04-03 17:30:24.657271','ADMIN','2024-04-03 17:30:24.657271','laptop.jpg','files/image-category',NULL),(14,'ADMIN','2024-04-03 17:30:54.948427','ADMIN','2024-04-03 17:30:54.948427','phone.webp','files/image-category',NULL),(15,'ADMIN','2024-04-04 09:01:55.649332','ADMIN','2024-04-04 09:01:55.649332','do_dung_hoc_tap.webp','files/image-category',NULL),(16,'ADMIN','2024-04-04 11:02:24.988624','ADMIN','2024-04-04 11:02:24.988624','sean_1.webp','files/image-product',20),(17,'ADMIN','2024-04-04 11:02:25.035463','ADMIN','2024-04-04 11:02:25.035463','sean_2.webp','files/image-product',20),(18,'ADMIN','2024-04-04 11:02:25.044470','ADMIN','2024-04-04 11:02:25.044470','sean_3.webp','files/image-product',20),(19,'ADMIN','2024-04-04 11:02:25.060441','ADMIN','2024-04-04 11:02:25.060441','snea - Copy.webp','files/image-product',20),(20,'ADMIN','2024-04-04 11:49:49.486457','ADMIN','2024-04-04 11:49:49.486457','mu_1.avif','files/image-product',7),(21,'ADMIN','2024-04-04 11:49:49.584718','ADMIN','2024-04-04 11:49:49.584718','mu1.webp','files/image-product',7),(22,'ADMIN','2024-04-04 11:49:49.608077','ADMIN','2024-04-04 11:49:49.608077','mu2.webp','files/image-product',7),(23,'ADMIN','2024-04-04 11:49:49.621210','ADMIN','2024-04-04 11:49:49.621210','mu3.webp','files/image-product',7),(24,'ADMIN','2024-04-04 11:50:34.994071','ADMIN','2024-04-04 11:50:34.994071','man1.webp','files/image-product',6),(25,'ADMIN','2024-04-04 11:50:35.046849','ADMIN','2024-04-04 11:50:35.046849','man2.webp','files/image-product',6),(26,'ADMIN','2024-04-04 11:50:35.060426','ADMIN','2024-04-04 11:50:35.060426','man3.webp','files/image-product',6),(27,'ADMIN','2024-04-04 11:50:35.066425','ADMIN','2024-04-04 11:50:35.066425','man4.webp','files/image-product',6),(28,'ADMIN','2024-04-04 11:50:35.077880','ADMIN','2024-04-04 11:50:35.077880','man5.webp','files/image-product',6),(29,'ADMIN','2024-04-04 15:55:28.155702','ADMIN','2024-04-04 15:55:28.155702','mu_1.avif','files/avatar',NULL),(33,'ADMIN','2024-04-05 09:28:12.101213','ADMIN','2024-04-05 09:28:26.192383','man3.webp','files/avatar',NULL);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `name_vn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,NULL,NULL,NULL,NULL,'ao liverpool','Áo liverpool'),(2,NULL,NULL,NULL,NULL,'laptop lenovo thinkpad','laptop lenovo thinkpad'),(3,NULL,NULL,NULL,NULL,'ao mancity','Áo mancity'),(4,'ADMIN','2024-03-19 21:17:39.902424','ADMIN','2024-03-19 21:17:39.902424','ao messi inter miami','Áo Messi Inter Miami'),(5,'ADMIN','2024-03-19 21:22:07.768176','ADMIN','2024-03-19 21:22:07.768176','ao argentina messi, ao messi, ao ms','Áo argentina Messi'),(6,'ADMIN','2024-03-20 19:52:57.302841','ADMIN','2024-03-20 19:52:57.302841','ao mancity chinh hang, ao mc, mc, mc chinh hang, ao mc chinh hang','Áo mancity chính hãng'),(7,'ADMIN','2024-03-20 19:55:23.740519','ADMIN','2024-03-20 19:55:23.740519','ao man manchester united chinh hang, ao mu chinh hang, ao man united chinh hang','Áo Manchester United Chính Hãng'),(19,'ADMIN','2024-04-04 10:56:40.216671','ADMIN','2024-04-04 10:56:40.216671','te','tes'),(20,'ADMIN','2024-04-04 10:57:53.960208','ADMIN','2024-04-04 10:57:53.960208','giay sneaker de trang','Giày sneaker đế trắng');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `payment` enum('PAY_AT_HOME','PAY_BY_BANK') DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `percent` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkp5k52qtiygd8jkag4hayd0qg` (`product_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkp5k52qtiygd8jkag4hayd0qg` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'ADMIN','2024-03-31 16:00:35.362636','ADMIN','2024-03-31 16:00:35.362636','PAY_AT_HOME',9,7,2,0),(3,'ADMIN','2024-04-04 15:52:08.970661','ADMIN','2024-04-04 15:52:08.970661','PAY_AT_HOME',1,2,4,0),(4,'ADMIN','2024-04-09 19:06:05.008372','ADMIN','2024-04-09 19:06:05.008372','PAY_AT_HOME',8,4,2,15),(5,'ADMIN','2024-04-09 19:41:58.721272','ADMIN','2024-04-09 19:41:58.721272','PAY_AT_HOME',4,3,2,15),(6,'ADMIN','2024-04-09 19:43:13.603459','ADMIN','2024-04-09 19:43:13.603459','PAY_AT_HOME',20,5,2,15);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TR_AfterInsert` AFTER INSERT ON `orders` FOR EACH ROW begin
        insert into bills(
                         created_by, created_date, modified_by,
                          modified_date, name, status, order_id
            )
            values (
                    NEW.created_by, NEW.created_date, NEW.modified_by,
                    NEW.modified_date, concat('Hóa đơn: ', (
                    SELECT
                        o.name_vn
                    FROM ecommerce.products p inner join ecommerce.languages o on p.language_id = o.id
                    where p.id = NEW.product_id
                )), 'PROCESSING', NEW.id
                   );
    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `description` text,
  `price` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `vendor_id` bigint DEFAULT NULL,
  `language_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_erc660ddddcmotkvbb85mhumo` (`language_id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKs6kdu75k7ub4s95ydsr52p59s` (`vendor_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKs6kdu75k7ub4s95ydsr52p59s` FOREIGN KEY (`vendor_id`) REFERENCES `vendors` (`id`),
  CONSTRAINT `FKtfnro803vqrg0ap1a1pmg0tn3` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'ADMIN','2024-03-16 08:23:54.924630','ADMIN','2024-03-16 08:23:54.924630','ÁO LIVERPOOL',1700000,120,1,1,1),(3,'ADMIN','2024-03-16 10:01:49.789719','ADMIN','2024-03-16 10:01:49.789719','RAM: 16GB LPDDR3 2133MHz Onboard\r\nỔ cứng: SSD 512GB M.2 PCIe NVMe\r\nMàn hình: 14.0\" FHD IPS, anti-glare, low power, 400 nits\r\nCPU: i7 10610U Processor with vPro™ (1.80 - 4.90 GHz, 4 Cores, 8 Threads, 8 MB Cache)\r\nVGA: Intel UHD Graphics\r\nPin: 51Wh',14900000,132,2,1,2),(4,'ADMIN','2024-03-19 21:17:39.895418','ADMIN','2024-03-19 21:17:39.895418','áo messi inter',125000000,12,1,1,4),(5,'ADMIN','2024-03-19 21:22:07.765155','ADMIN','2024-03-19 21:22:07.765155','ao argentina messi, Áo argentina Messi ao messi',3500000,120,1,1,5),(6,'ADMIN','2024-03-20 19:52:57.299333','ADMIN','2024-03-20 19:52:57.299333','Áo mancity chính hãng',1200000,120,1,2,6),(7,'ADMIN','2024-03-20 19:55:23.740519','ADMIN','2024-03-20 19:55:23.740519','Áo Manchester United Chính Hãng',500000,120,1,2,7),(20,'ADMIN','2024-04-04 10:57:53.960208','ADMIN','2024-04-04 10:57:53.960208','Giày sneaker đế trắng',230000,500,5,2,20);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TR_Product_AfterInsert` AFTER INSERT ON `products` FOR EACH ROW begin
        insert into track_product_sellers
            (created_by, created_date,
             modified_by, modified_date,
            number_of_products_sold,
             product_id)
        VALUES (
                NEW.created_by, NEW.created_date,
                NEW.modified_by, NEW.modified_date,
                0, NEW.id
               );
    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `track_product_sellers`
--

DROP TABLE IF EXISTS `track_product_sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track_product_sellers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `number_of_products_sold` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_esaro70y0oaop8cdng4osne7n` (`product_id`),
  CONSTRAINT `FK5x3edsk7oupt61dhdjc2s42b5` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track_product_sellers`
--

LOCK TABLES `track_product_sellers` WRITE;
/*!40000 ALTER TABLE `track_product_sellers` DISABLE KEYS */;
INSERT INTO `track_product_sellers` VALUES (1,'ADMIN','2024-03-16 08:23:54.924630','ADMIN','2024-03-16 08:23:54.924630',1,2),(2,'ADMIN','2024-03-16 10:01:49.789719','ADMIN','2024-03-16 10:01:49.789719',0,3),(3,'ADMIN','2024-03-19 21:17:39.895418','ADMIN','2024-03-19 21:17:39.895418',0,4),(4,'ADMIN','2024-03-19 21:22:07.765155','ADMIN','2024-03-19 21:22:07.765155',0,5),(5,'ADMIN','2024-03-20 19:52:57.299333','ADMIN','2024-03-20 19:52:57.299333',0,6),(6,'ADMIN','2024-03-20 19:55:23.740519','ADMIN','2024-03-20 19:55:23.740519',1,7),(19,'ADMIN','2024-04-04 10:57:53.960208','ADMIN','2024-04-04 10:57:53.960208',0,20);
/*!40000 ALTER TABLE `track_product_sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
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
  `image_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_94dj9ry3k3tmcsyg8eatp7vvn` (`image_id`),
  CONSTRAINT `FK17herqt2to4hyl5q5r5ogbxk9` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ADMIN','2024-03-14 18:57:02.648574','ADMIN','2024-03-29 07:11:45.447038','quangphu.re0502@gmail.com','$2a$10$rW5Jx/8MMq6/5CYnRFOXruld/3Av.gjeIpgGONH.QAYFTuzeHjYCu','ADMIN','PHU LUU',NULL,'PHU NGUYEN','0982370967','test',NULL,'test',8),(2,NULL,'2024-03-15 09:42:23.093861','ADMIN','2024-04-05 09:28:12.129488','quangphu2050@gmail.com','$2a$10$4WepOirdpJfhsSXXmlQEJ.h3SOlZndGwLX/Ar1sClK9O76hkVKvbW','ADMIN','bac ninh phu luu',NULL,'Phú Nguyễn Quang','0982370967','BAC NINH',NULL,'irohas2004',33),(3,NULL,'2024-03-19 19:33:05.659325','ADMIN','2024-03-19 19:43:13.582870','quangphu2060@gmail.com','$2a$10$4WepOirdpJfhsSXXmlQEJ.h3SOlZndGwLX/Ar1sClK9O76hkVKvbW','VENDOR',NULL,NULL,NULL,NULL,NULL,NULL,'quangphu',NULL),(4,NULL,'2024-03-19 21:33:51.674436','ADMIN','2024-04-04 15:55:28.219651','nguyenquangphu05022004@gmail.com','$2a$10$jUth3cSIoUnBCW37xf7qKutsiQDxnRDMU5X7DMBcYPyxA4TNlZywa','VENDOR','test',NULL,'test','0890980','test',NULL,'user',29),(6,NULL,'2024-03-28 16:36:00.463221','ADMIN','2024-04-07 11:51:21.139126','quangphu2060@gmail.com','$2a$10$52Dt0Ocg7s3OcezrbH5Cye9db8RZi66i.M4BsxSQlppD14cSzcque','VENDOR','Phu Luu Trung Nghia Yen Phong Bac Ninh',NULL,'Nguyễn Quang Phú','0982370967',NULL,NULL,'studytonight',5);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `per_money_delivery` int DEFAULT NULL,
  `shop_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i4fsfxe266yl9oye02ongwl8` (`user_id`),
  CONSTRAINT `FKiuqso7j7nivq7sb3v3v4j7ein` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (1,'ADMIN','2024-03-19 19:43:13.532083','ADMIN','2024-03-19 19:43:13.532083',15000,'shop quang phu',3),(2,'ADMIN','2024-03-20 19:51:35.221903','ADMIN','2024-03-20 19:51:35.221903',15000,'kimdung',4),(3,'ADMIN','2024-04-07 11:51:20.966430','ADMIN','2024-04-07 11:51:20.966430',NULL,'study-night',6);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verify`
--

DROP TABLE IF EXISTS `verify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verify` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `code` varchar(12) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_trg61pfj9e6nx1yc39we3f8ox` (`code`),
  UNIQUE KEY `UK_eoloox20buv32h1yqgvwls3q8` (`user_id`),
  CONSTRAINT `FKnf9v9on2xkg70ky8hnaijt1a4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verify`
--

LOCK TABLES `verify` WRITE;
/*!40000 ALTER TABLE `verify` DISABLE KEYS */;
INSERT INTO `verify` VALUES (1,NULL,'2024-03-24 15:04:54.038874',NULL,'2024-03-27 10:59:54.456596','941504838813',_binary '\0',2);
/*!40000 ALTER TABLE `verify` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-11  6:12:26
