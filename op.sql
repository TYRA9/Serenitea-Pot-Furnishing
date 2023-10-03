-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: op
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Current Database: `op`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `op` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `op`;

--
-- Table structure for table `furnishing`
--

DROP TABLE IF EXISTS `furnishing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `furnishing` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `enterprise` varchar(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `price` decimal(11,2) NOT NULL,
  `sales` int unsigned NOT NULL,
  `stock` int unsigned NOT NULL,
  `img_path` varchar(256) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `furnishing`
--

LOCK TABLES `furnishing` WRITE;
/*!40000 ALTER TABLE `furnishing` DISABLE KEYS */;
INSERT INTO `furnishing` VALUES (1,'垂香木朱漆圆桌','琉璃阁(璃月)',5000.00,120,500,'resources/images/product-image/2023/10/2/887e3d68-8957-4e9e-8b67-5b248ffd54e3_1696256111692_1.1.png'),(2,'垂香木商铺立柜','琉璃阁(璃月)',3500.00,400,320,'resources/images/product-image/2023/10/2/becb11ee-a896-4ffc-bf10-1f47c33ff048_1696256229624_2.1.png'),(3,'朱漆垂香木卷轴书架','飞云商会',15000.00,35,18,'resources/images/product-image/2023/10/2/f058a484-5462-47bb-aa79-7e319482363f_1696256324330_3.1.png'),(4,'松木折屏_云来帆影','群玉阁',9998.00,1002,300,'resources/images/product-image/2023/10/2/bf803590-1dcd-44a3-9c34-fe70794a0f23_1696256499051_10.1.png'),(5,'松木靠背茶椅','飞云商会',3998.00,30,12,'resources/images/product-image/2023/10/2/c26e10c1-788f-4946-b59f-0ca6bd9fc11c_1696256544619_4.1.png'),(6,'柔风加护的床榻','蒙德西风骑士团',24988.00,111,23,'resources/images/product-image/2023/10/2/282ba4b1-255f-459d-8b13-24fbf8968237_1696256602928_6.1柔风加护的床榻.png'),(7,'柔软的会客厅沙发','蒙德西风骑士团',32000.00,8,11,'resources/images/product-image/2023/10/2/4ec2403c-1968-4fe7-8fd8-1b8559ddad47_1696256661362_7.1.png'),(8,'花卉瓶栽_盛放的曙红','轻策庄',500.00,300,100,'resources/images/product-image/2023/10/2/b89f116a-3524-44e3-b3ec-5a54b1917925_1696256758619_8.1.png'),(9,'纸墨笔砚_临池学书','飞云商会',1000.00,400,1000,'resources/images/product-image/2023/10/2/e936c4a2-c82e-4816-8d52-5ce9236c0d9a_1696256818312_9.1.png'),(10,'斑斓与清冽之夏','万国商会',50000.00,7,20,'resources/images/product-image/2023/10/2/cfcd6c11-c2ae-4780-903e-d9afd7bf6d87_1696256943003_11.1.png');
/*!40000 ALTER TABLE `furnishing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `number` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  `date_time` datetime NOT NULL,
  `sums` decimal(11,2) NOT NULL,
  `status` tinyint NOT NULL,
  `uid` int unsigned NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `unit_price` decimal(11,2) NOT NULL,
  `cnt` int unsigned NOT NULL DEFAULT '0',
  `total_price` decimal(11,2) NOT NULL,
  `order_number` varchar(64) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pot_manager`
--

DROP TABLE IF EXISTS `pot_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pot_manager` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `password` char(32) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pot_manager`
--

LOCK TABLES `pot_manager` WRITE;
/*!40000 ALTER TABLE `pot_manager` DISABLE KEYS */;
INSERT INTO `pot_manager` VALUES (1,'Cyan','f9c2a295848adbf60f47652573931988');
/*!40000 ALTER TABLE `pot_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pot_user`
--

DROP TABLE IF EXISTS `pot_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pot_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(32) COLLATE utf8mb3_bin NOT NULL,
  `email` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pot_user`
--

LOCK TABLES `pot_user` WRITE;
/*!40000 ALTER TABLE `pot_user` DISABLE KEYS */;
INSERT INTO `pot_user` VALUES (1,'Rain','cd13b6a6af66fb774faa589a9d18f906','Rain@rain.com'),(2,'Five','9b1469b119f7f99bcd6fd6651b4432bd','Five@five.com'),(3,'Cyan','f9c2a295848adbf60f47652573931988','WangwuEX@outlook.com'),(4,'Eisen','306f52917420599d7202c8c70b6f53c3','Eisen@eisen.com'),(5,'Kyrie','5e4d614d1c5e99716f23462a4e6aba4d','Kyrie@kyrie.com'),(6,'Blue','455deec2a9cf6751686b8408f558ba28','Blue@blue.com'),(7,'White','22c8bc4b667eb9401ab42182aa4bf496','White@white.com'),(8,'Kevin','8cc4e34dc1ad80356b61787412e0dfd7','Kevin@kevin.com');
/*!40000 ALTER TABLE `pot_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `fid` int unsigned NOT NULL,
  `name` varchar(64) COLLATE utf8mb3_bin NOT NULL DEFAULT '',
  `unit_price` decimal(11,2) NOT NULL,
  `cnt` int unsigned NOT NULL DEFAULT '0',
  `total_price` decimal(11,2) NOT NULL,
  `uid` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (3,3,'朱漆垂香木卷轴书架',15000.00,1,15000.00,3),(4,4,'松木折屏_云来帆影',9998.00,1,9998.00,3);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-03 10:41:01
