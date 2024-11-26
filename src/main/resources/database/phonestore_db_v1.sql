-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: phonestore_db
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhq1m50l0ke2fkqxxd6ubo3x4b` (`cart_id`),
  KEY `FKngo5q1x6m7sudq0m8ylo5prrh` (`product_id`),
  CONSTRAINT `FKhq1m50l0ke2fkqxxd6ubo3x4b` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKngo5q1x6m7sudq0m8ylo5prrh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `prioritize` int NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Iphone',1,'iphone'),(2,'Samsung Galaxy',2,'samsung'),(3,'Oppo Series',3,'oppo'),(4,'XiaoMi',4,'xiaomi'),(5,'Vivo',5,'vivo'),(6,'Realme',6,'realme'),(7,'Nokia',10,'nokia'),(8,'Votus',53,'votus');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FKinivj2k1370kw224lavkm3rqm` (`product_id`),
  CONSTRAINT `FKinivj2k1370kw224lavkm3rqm` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `order_details_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr0jrq3lxgukm1og01gyu038kl` (`order_details_id`),
  CONSTRAINT `FKr0jrq3lxgukm1og01gyu038kl` FOREIGN KEY (`order_details_id`) REFERENCES `order_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `delivery_date` datetime(6) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ship_address` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `battery` int NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `date_of_manufacture` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount` double NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `ram` int NOT NULL,
  `sale_number` int NOT NULL,
  `screen` double NOT NULL,
  `stock_number` int NOT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,4422,1,'black','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 15',20,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055234/dailedev/product/iphone-15.jpg_20241028015353.jpg','Iphone 15',30000000,8,100,6.7,2100,1),(2,4422,1,'black','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 15 Pro Max',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055308/dailedev/product/iphone-image.jpg_20241028015507.jpg','Iphone 15 Pro Max',35000000,8,10,6.7,2100,1),(3,4421,1,'blue','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 14 Pro Max',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055319/dailedev/product/iphone-13.jpg_20241028015519.jpg','Iphone 14 Pro Max',35000000,8,10,6.7,2100,1),(4,4422,1,'blue','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 14 Pro Max',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055328/dailedev/product/iphone-image.jpg_20241028015528.jpg','Iphone 14  ',35000000,8,10,6.7,2100,1),(5,4422,1,'black','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 13 128GB',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055344/dailedev/product/iphone-13.jpg_20241028015544.jpg','Iphone 13 ',35000000,8,10,6.7,2100,1),(7,4422,1,'black','Apple A17 Pro Bionic 6 nhân','2024-09-26 07:00:00.000000','Iphone 15 Pro Max',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055358/dailedev/product/iphone-15.jpg_20241028015557.jpg','Iphone 15 Pro Max',35000000,8,10,6.7,2100,1),(10,2000,2,'Red','12','2024-01-01 07:00:00.000000','Samsung S23 Ultra new 100%',5,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055367/dailedev/product/galaxy-s23-ultra.jpg_20241028015607.jpg','Samsung S23 Ultra ',29000000,8,95,6.2,100,4),(12,123,8,'black','Apple A17 Pro Bionic 6 nhân','2024-08-30 07:00:00.000000','Iphone 12 New ',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730055381/dailedev/product/oppo-image.jpg_20241028015620.jpg','Votus x2',3000000,12,0,133,12,4),(13,2621,3,'Yellow','160','2024-09-26 00:00:00.000000','Oppo New',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730056542/dailedev/product/oppo_a93.jpg_20241028021539.jpg','Oppo A91',10000000,4,1222,6.1,5000,2),(14,3000,2,'Black','Snapdragon 720G','2023-01-09 07:00:00.000000','Samsung Galaxy M31',15,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058189/dailedev/product/galaxy-z-flip4-onleaks-311.jpg_20241028024308.jpg','Samsung M31',5000000,6,120,6.4,1000,4),(15,3500,4,'Blue','Snapdragon 750G','2023-02-14 07:00:00.000000','Xiaomi Mi 10T',15,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058209/dailedev/product/Xiaomi-Redmi-Note-13.jpg_20241028024329.jpg','Xiaomi Mi 10T',8000000,8,220,6.67,1200,2),(16,4000,2,'Silver','Exynos 9611','2023-03-19 07:00:00.000000','Samsung Galaxy A51',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058236/dailedev/product/sss23.jpg_20241028024356.jpg','Samsung A51',7000000,4,340,6.5,900,4),(17,4500,4,'Green','Snapdragon 865','2023-04-25 00:00:00.000000','OnePlus 8T',20,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058348/dailedev/product/Xiaomi-11T-Pro-Blue-1-2-.jpg_20241028024548.jpg','OnePlus 8T',9000000,12,200,6.55,800,3),(18,5000,3,'Red','Dimensity 1000+','2023-05-30 00:00:00.000000','Oppo Reno5',18,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058399/dailedev/product/crop_oppo-a18-didongmy-.jpg_20241028024638.jpg','Oppo Reno5',8500000,8,250,6.43,700,2),(64,3200,1,'Gray','Kirin 820','2023-06-14 07:00:00.000000','Iphone 15 Plus Like New',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058507/dailedev/product/71vKy5OHuPL.jpg_20241028024826.jpg','Iphone 15 Plus',6000000,6,300,6.4,500,1),(65,4000,4,'Black','Snapdragon 732G','2023-07-09 07:00:00.000000','Xiaomi Poco X3',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058532/dailedev/product/Xiaomi-11T-Pro-Blue-1-2-.jpg_20241028024851.jpg','Xiaomi Poco X3',7500000,6,400,6.67,800,2),(66,4500,2,'White','Exynos 9820','2023-08-04 07:00:00.000000','Samsung Galaxy S10',20,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058565/dailedev/product/sss23.jpg_20241028024924.jpg','Samsung S10',12000000,8,500,6.1,600,1),(67,5000,4,'Red','Snapdragon 865+','2023-09-20 00:00:00.000000','Asus ROG Phone 3',25,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058593/dailedev/product/Xiaomi-11T-Pro-Blue-1-2-.jpg_20241028024953.jpg','Asus ROG 3',15000000,12,600,6.59,700,3),(68,3000,6,'Gold','Helio G95','2023-10-14 07:00:00.000000','Realme 7 Pro',8,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058636/dailedev/product/anh-1_1280x720-800-resize.jpg_20241028025036.jpg','Realme 7 Pro',7000000,6,700,6.4,1000,6),(69,3300,3,'Blue','Snapdragon 730G','2023-11-09 07:00:00.000000','Oppo F17 Pro',15,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058656/dailedev/product/oppo-image.jpg_20241028025056.jpg','Oppo F17 Pro',8000000,8,320,6.43,500,2),(70,4000,6,'Green','Dimensity 800U','2024-01-19 07:00:00.000000','RealMe 8',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058681/dailedev/product/realme-9i-17_1280x720-800-resize.jpg_20241028025121.jpg','RealMe 8',8500000,8,420,6.44,600,4),(71,5000,4,'Black','Snapdragon 870','2024-02-25 00:00:00.000000','Xiaomi Mi 11',18,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058760/dailedev/product/Xiaomi-Redmi-Note-12.jpg_20241028025240.jpg','Xiaomi Mi 11',13000000,12,520,6.81,700,2),(72,4500,2,'Purple','Exynos 2100','2024-03-14 07:00:00.000000','Samsung Galaxy S21',20,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058822/dailedev/product/galaxy-s24-ultra.jpg_20241028025341.jpg','Samsung S21',16000000,8,650,6.2,600,4),(73,4100,7,'Yellow','Kirin 9000','2024-04-09 07:00:00.000000','Huawei Mate 40 Pro',22,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058857/dailedev/product/danh-gia-Galaxy-A32-9-750x420.jpg_20241028025417.jpg','Huawei Mate 40 Pro',18000000,8,300,6.76,500,4),(74,3800,3,'Gray','Snapdragon 768G','2024-05-20 00:00:00.000000','Oppo Reno6',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058875/dailedev/product/oppo_a93.jpg_20241028025434.jpg','Oppo Reno6',9500000,8,400,6.43,1000,6),(75,4500,4,'Blue','Snapdragon 888','2024-06-05 00:00:00.000000','OnePlus 9',15,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058890/dailedev/product/Xiaomi-11T-Pro-Blue-1-2-.jpg_20241028025449.jpg','OnePlus 9',14000000,12,520,6.55,800,3),(76,5000,6,'Red','Helio G85','2024-07-24 07:00:00.000000','Xiaomi Redmi Note 10',12,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058907/dailedev/product/realme-9i-17_1280x720-800-resize.jpg_20241028025506.jpg','Redmi Note 10',6500000,6,280,6.5,600,2),(77,4200,2,'White','Snapdragon 690','2024-08-14 07:00:00.000000','Nokia X10',18,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730058934/dailedev/product/anh-1_1280x720-800-resize.jpg_20241028025534.jpg','Nokia X10',7000000,6,310,6.67,900,6),(78,4100,5,'Black','Snapdragon 750G','2024-09-29 07:00:00.000000','Vivo New',10,'https://res.cloudinary.com/dwwlenn7f/image/upload/v1730059035/dailedev/product/anh-1_1280x720-800-resize.jpg_20241028025714.jpg','Vivo 5',8000000,8,250,6.7,1000,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'02 Công Xã Paris, Quận 1, Tp.HCM','Nhà cung cấp Iphone','apple@gmail.com','Apple'),(2,'02 Thảo Điền, Quận 2, Tp.HCM (TTBH Thuận Mỹ)','Nhà cung cấp Oppo','oppo@gmail.com','Oppo'),(3,'32 Cách Mạng Tháng 8, Phường 6, Quận 3, Tp.HCM','Nhà cung cấp Vivo','vivo@gmail.com','Vivo'),(4,'KCN Yên Phong 1 - Xã Yên Trung - Huyện Yên Phong - Tỉnh Bắc Ninh','Nhà cung cấp Samsung','samsung@gmail.com','Samsung'),(6,'Quận Hoàng Mai, Hà Nội','Nokias ...','nokia@gmail.com','Nokia');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'user',
  `status` bit(1) DEFAULT NULL,
  `url_avatar` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2024-10-29  1:53:25
