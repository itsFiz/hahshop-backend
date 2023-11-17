/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.35 : Database - ecommerce_store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecommerce_store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ecommerce_store`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `postcode` int NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `address` */

insert  into `address`(`id`,`city`,`postcode`,`street`) values 
(1,'Kuala Lumpur',50350,'Sunway Putra'),
(2,'Selangor',40400,'Rawang'),
(3,'Kuala Lumpur',58000,'Mid Valley Megamall'),
(4,'Selangor',44000,'Klang'),
(5,'Kuala Lumpur',44000,'Jalan Raja Laut'),
(6,'Kuala Lumpur',55100,'Bukit Bintang'),
(7,'Demo City',123456,'Demo Street'),
(8,'Demo City',123456,'Demo Street'),
(9,'Kuala Lumpur',50400,'Pavillion Bukit Bintang'),
(10,'Kuala Lumpur',51200,'Permai Ria Condo, Jalan Ipoh'),
(11,'Demo City',123456,'Demo Street'),
(12,'Singapore',44400,'Chinatown'),
(13,'Kuala Lumpur',56200,'Level 3, 302B, Petronas Twin Tower'),
(14,'Spain',22000,'Madrid'),
(15,'Kuala Lumpur',55100,' Level 5, Pavilion KL, 168, Jln Bukit Bintang,'),
(16,'Kuala Lumpur ',0,'Midvalley'),
(17,'Kuala Lumpur',50400,'Midvalley'),
(18,'Kuala Lumpur ',50400,'Sunway Putra'),
(19,'Kuala Lumpur',50400,'Midvalley'),
(20,'Spain',909887,'Madrid'),
(21,'KL',41000,'UOB'),
(22,'',0,'Midvalley');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `added_time` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart` */

insert  into `cart`(`id`,`added_time`,`quantity`,`product_id`,`user_id`) values 
(18,'1700150364852',1,10,9);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`description`,`name`,`status`) values 
(2,'Explore the latest in cutting-edge technology, from smartphones to smart home devices, ensuring you stay ahead in the digital ','Electronic & Gadgets','Active'),
(3,'Discover trendy and timeless fashion pieces, from haute couture to casual wear, to elevate your style and make a statement.','Fashion & Apparel','Active'),
(4,'Foster creativity and fun for all ages with an extensive collection of toys, games, and educational activities that entertain and inspire.','Toys & Games','Active'),
(5,'Elevate your active lifestyle with high-quality sports gear, fitness equipment, and outdoor essentials for enthusiasts and adventurers alike.','Sports & Outdoors','Active'),
(6,'Unleash your beauty potential with a range of skincare, cosmetics, and grooming products that enhance your natural radiance.','Beauty & Personal Care','Active'),
(8,'Extensive collection of footwear that caters to every occasion, from casual strolls to sports and formal events.','Footwear','Active'),
(11,' Embark on a gastronomic journey with our curated selection of premium food items. From delectable snacks and artisanal chocolates to exotic spices and gourmet sauces, our e-commerce platform offers a diverse array of culinary delights','Food & Beverage','Active'),
(12,'test','Gifts','Deactivated'),
(13,'gift','Gifts','Active');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `delivery_date` varchar(255) DEFAULT NULL,
  `delivery_status` varchar(255) DEFAULT NULL,
  `delivery_time` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `order_time` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `delivery_person_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdx1lfeyugmmym8gm0fbafb6vx` (`delivery_person_id`),
  KEY `FK787ibr3guwp6xobrpbofnv7le` (`product_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FK787ibr3guwp6xobrpbofnv7le` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKdx1lfeyugmmym8gm0fbafb6vx` FOREIGN KEY (`delivery_person_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`id`,`delivery_date`,`delivery_status`,`delivery_time`,`order_id`,`order_time`,`quantity`,`status`,`delivery_person_id`,`product_id`,`user_id`) values 
(1,NULL,'Pending',NULL,'HIZJHOFHJ4868HTC','1699533805589',2,'Pending',12,2,9),
(2,NULL,'Pending',NULL,'K4YUFHMOSFM34CV1','1699599242338',2,'Pending',NULL,9,9),
(3,NULL,'Pending',NULL,'X1FSD3G0JHTYCBI4','1699953142364',1,'Pending',NULL,14,17),
(4,NULL,'Pending',NULL,'X1FSD3G0JHTYCBI4','1699953142364',1,'Pending',NULL,4,17),
(5,NULL,'Pending',NULL,'PQZBY1JEPOXXCUE8','1700017012668',1,'Pending',NULL,18,17),
(6,NULL,'Pending',NULL,'PQZBY1JEPOXXCUE8','1700017012668',1,'Pending',NULL,19,17),
(7,NULL,'Pending',NULL,'COLRSIJEICTUAFRC','1700030309379',1,'Pending',NULL,25,18),
(8,'2023-11-16','Pending','Night','VMA5KNTAPIRIPPLZ','1700042293302',1,'On the way',23,21,17),
(9,'2023-11-16','Pending','Night','VMA5KNTAPIRIPPLZ','1700042293302',1,'On the way',23,22,17),
(10,NULL,'Pending',NULL,'G9VXNGYVKISXMEEJ','1700108833573',2,'Pending',NULL,9,9),
(11,NULL,'Pending',NULL,'DDCQSFOTO2FRXPXK','1700109915869',1,'Pending',NULL,1,17),
(12,NULL,'Pending',NULL,'B05RDABIQFRDP12A','1700150231524',1,'Pending',NULL,1,9),
(13,NULL,'Pending',NULL,'VPMOBFGYY48VO26I','1700183863999',1,'Pending',NULL,1,25),
(14,NULL,'Pending',NULL,'4FSN0G0T2MTGZSXD','1700189360296',2,'Pending',NULL,30,29),
(15,NULL,'Pending',NULL,'HZKEWZDGE2XK1FKJ','1700189787753',1,'On the way',NULL,31,17);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `quantity` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `seller_user_id` int DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `image3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKjh9cre5eds0b96q9dulabyjbu` (`seller_user_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKjh9cre5eds0b96q9dulabyjbu` FOREIGN KEY (`seller_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`description`,`image1`,`name`,`price`,`quantity`,`status`,`category_id`,`seller_user_id`,`image2`,`image3`) values 
(1,'Men shoes','2e672e50017649d897b9d415464b821e.webp','Nike Blazer Mid \'77 Vintage',389.00,96,'Active',8,22,NULL,NULL),
(2,'iPhone 15 brings you Dynamic Island','iphone15.jpeg','Apple iPhone 15',4399.00,45,'Active',2,8,NULL,NULL),
(4,'Gaming console','ps5.jpeg','Sony PS5',1999.00,98,'Active',2,10,NULL,NULL),
(5,'Casual Tee','pstshirt.jpeg','Playstation T-Shirt',99.00,99,'Deactivated',3,10,NULL,NULL),
(8,'Airpods','airpodpro2.jpeg','AirPods Pro (2nd generation) - Lightning',1099.00,12,'Active',2,8,NULL,NULL),
(9,'Action Figure','spider-verse.png','Funko Pop Spider Verse',269.00,95,'Active',4,10,NULL,NULL),
(10,'Sweater that can be changes inside out','reversibleparka.avif','Reversible Parka',129.00,99,'Deactivated',3,13,NULL,NULL),
(11,'Waterproof jacket','blocktech.jfif','Blocktech Parka',299.00,99,'Deactivated',3,13,NULL,NULL),
(12,'Hah Official Tshirt','6c0d9a8649a74eb0b9250cdfcc22520c.jfif','Hah Tshirt',59.00,200,'Active',3,16,NULL,NULL),
(13,'Disc version','a39e3a06d15d45b8b935e4d8942bb530.webp','Marvel Spider Man 2',299.00,99,'Deactivated',2,10,NULL,NULL),
(14,'Disc Version','4bc6810e83224eb78ef8981c72426f96.webp','Marvel Spider Man 2',299.00,19,'Active',2,10,NULL,NULL),
(15,'Special collector edition ','e838c201150c472b8cfd88dd51de3d70.jpg','Marvel Spider Man 2 (Collector Edition)',1500.00,25,'Active',4,10,NULL,NULL),
(16,'Action Figure','dfbdfc7a2ba345558490f2ee70b565a5.png','VALORANT Jett Statue',849.00,20,'Deactivated',4,10,NULL,NULL),
(17,'Action Figure','371a8d0992de44deaefe950fa294ccfc.png','VALORANT Phoenix Statue',849.00,20,'Active',4,19,NULL,NULL),
(18,'Action Figure','0749b58f6e8149a289093b36fc60b313.png','VALORANT Jett Statue',849.00,24,'Active',4,19,NULL,NULL),
(19,'Action Figure','85ed6a22399c457f90f1cbd169c40895.png','VALORANT Reyna Statue',849.00,32,'Active',4,19,NULL,NULL),
(20,'Action Figure','ce188004779f451bb5c89af6cd9114f0.png','VALORANT Killjoy Statue',849.00,33,'Active',4,19,NULL,NULL),
(21,'Sunscreen','6d47d49855984a16bc1492874a7a85c0.jfif','Cosrx Aloe Sun Cream',59.00,25,'Active',6,20,NULL,NULL),
(22,'Hydra Power Essence','ad1a6c2acf6d47aa9c4e4f5d115e5390.jfif','Cosrx Hyaluronic Acid ',49.00,22,'Active',6,20,NULL,NULL),
(23,'The latest M1 chip','f9b3322457ef419e9f6c7b58bb8cd454.webp','iPad Air 5th Gen',3999.00,20,'Active',2,8,NULL,NULL),
(24,'Carhatt','b42c8e1ccb994f5cb77c1f402a19dfaa.webp','Cargo Pants',499.00,12,'Deactivated',3,11,NULL,NULL),
(25,'Carhatt','4e25c4b7c6224c92a97b6d2a86802cf3.webp','Cargo Pants',499.00,1,'Deactivated',3,11,NULL,NULL),
(26,'test','7b01d1b2fb8642f885f8dc8d196d4a4f.webp','test',112.00,2,'Deactivated',3,19,NULL,NULL),
(27,'test','4e428999544b46eeb49959d1794dffbd.webp','Marvel Spider Man 2',33.00,3,'Deactivated',3,19,NULL,NULL),
(29,'Fine chocolate','72c9a3a27587410b8e7fdc0478646e43.jpg','Milk & White',11.00,33,'Active',11,24,NULL,NULL),
(30,'Vans shoe','094eb76e1a7d480eab0cd5023e4e9452.jpg','Vans X Stranger Things - Old skool',299.00,38,'Active',8,26,NULL,NULL),
(31,'cap','6395208734724823a136ac56c9f0ef60.jfif','Cap',59.00,19,'Active',3,30,NULL,NULL),
(32,'skateboard shoe','9a6adb269932454aa07e3a479d2fb76e.avif','Vans X Stranger Things Sk8 Hi',539.00,99,'Active',8,26,NULL,NULL),
(33,'Nike shoe','72924d5747724c99951274b0bf45de44.webp','Nike Dunk Low SE \'Multi Camo\'',1188.00,2,'Active',8,22,NULL,NULL),
(34,'Nike shoe','b6f67788ea8242c1a29e7c7168342473.webp','Nike Dunk Retro SE',439.00,99,'Active',8,22,NULL,NULL),
(35,'nike shoe','64f39892520144c59a5c79caa711253b.webp','Nike Dunk Low \'Panda\'',439.00,99,'Active',8,22,NULL,NULL),
(36,'nike shoe','d16e1340e5a949b79b50e48d3d716e92.webp','Nike Dunk Low SB \'Raygun Tie-Dye White\'',2500.00,3,'Active',8,22,NULL,NULL);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `review` varchar(255) DEFAULT NULL,
  `star` int NOT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `review` */

insert  into `review`(`id`,`review`,`star`,`product_id`,`user_id`) values 
(1,'Cute funko pop',5,9,9),
(2,'Best game ever',5,14,17),
(3,'Worth every penny',5,15,17),
(4,'Style',4,1,25);

/*Table structure for table `upload` */

DROP TABLE IF EXISTS `upload`;

CREATE TABLE `upload` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `upload` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dhlcfg8h1drrgu0irs1ro3ohb` (`address_id`),
  KEY `FK1tx8uqi51iytamsqqndcv0cdq` (`seller_id`),
  CONSTRAINT `FK1tx8uqi51iytamsqqndcv0cdq` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKddefmvbrws3hvl5t0hnnsv8ox` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`email_id`,`first_name`,`last_name`,`password`,`phone_no`,`role`,`status`,`address_id`,`seller_id`) values 
(6,'demo.admin@demo.com',NULL,NULL,'$2a$10$hD.NlSX.6psLW6rWhnStGeE6IBWoGS986XzNh66nimMniuPkWKOgq',NULL,'Admin','Active',NULL,NULL),
(7,'admin@hahshop.com',NULL,NULL,'$2a$10$Yh6V86zgDrxEfJqLkgr.4OWqBpp5FqEcQtg2GffWTSwqKyVOJYxie',NULL,'Admin','Active',NULL,NULL),
(8,'switch.os@hahshop.com','Switch','Official Store','$2a$10$39lp/bfThrQH0YD87bXoau5ifIXlwqiaN3Y.p4g1r.YjGTlbXd1GS','0123456789','Seller','Active',1,NULL),
(9,'alifdanish74@gmail.com','Alif','Danish','$2a$10$cUq.6xixoFXmtPerZv/WF.RAfIeH0akKs.fpmZZdx//1pbnL588Li','01127877926','Customer','Active',2,NULL),
(10,'sony.os@hahshop.com','Sony','Official Store','$2a$10$FffV6s6Z3ww1RewAIGiWnuWq9r955q1/1JmJZfTjAlbFI2rF8/.lu','0194938654','Seller','Active',3,NULL),
(11,'harith@hahshop.com','Harith','Nasahruddin','$2a$10$Hzddj2B0/EIQHKiDVID5suMqeRxYmhNj3WDn5VwPCtDEtGe08dZDm','0123456789','Seller','Deactivated',4,NULL),
(12,'poslaju@express.com','Poslaju','Express','$2a$10$Ij2MdTGSacu0nV2tFQhe0erw.z1LfaBIgy14Gi3qqpa.2ShFessO.','0387651612','Delivery','Active',5,8),
(13,'uniqlo@hahshop.com','Uniqlo','MY ','$2a$10$SoVvfGI2MOd.YCd/Ai11BeTUKb57KO8B3D03rEExh9HZ5Ck.opagy','036753428','Seller','Deactivated',6,NULL),
(15,'harith@gmail.com','Harith','Shaikh','$2a$10$LW9yQFqIOkRmdHibCQ74gOsTl87lAOhO71/p3Di370GAOKJAijdXi','1234567890','Customer','Active',8,NULL),
(16,'hahshop@hahshop.com','hahshop','officialstore','$2a$10$9LpmDnGrPKx1e4O5ZSmIT.yfgh5onUGk6G7Ts3ssHX10xUL6uSfIO','0123456789','Seller','Active',9,NULL),
(17,'hfzkdr@icloud.com','Hafiz','Kadir','$2a$10$0AGjEPG3js3DUsnT88axcOrV4nYWvUCaBg6otVdmSOgbY2o6dDjvm','0198481727','Customer','Active',10,NULL),
(18,'loki@gmail.com','Loki','Meow','$2a$10$6MdMRPLj8NJccraUCH4MmOnz5MXc.g6P4/AT1N0Wp.k0wJoOqGcR.','1234567890','Customer','Active',11,NULL),
(19,'riotgames@hahshop.com','Riot Games','Official Store','$2a$10$nyFW3INj3NrEKCoK/JZ6DOmpRgTFbumsoGzyEGh7V/qwzdFIhUNVS','0172377272','Seller','Active',12,NULL),
(20,'cosrx@hahshop.com','Cosrx','Official Store','$2a$10$A1WulOSQ24Ta0MyzZpMeN.17Xy0ZRFmCUGn2xAw6D1If.VCdBRmWa','0198987878','Seller','Active',13,NULL),
(21,'karimbenzema@icloud.com','Karim','Benzema','$2a$10$.idMSYJb5oCPoZmu4twGLuaI5qsdeUKeuOBoODRtZWMa7xNOJmEe2','0178481727','Customer','Active',14,NULL),
(22,'nike@hahshop.com','Nike','Official Store','$2a$10$qgi18hYWJ20AtnwN8/fxnuc7AhFpP.IVbc2i1sLgfPtuiWBJhbTKS','0176754673','Seller','Active',15,NULL),
(23,'ninjavan@hahshop.com','Ninja','Van','$2a$10$lZNAnbbv4Us3kz8hl7Uu6.1BAw07R5QqKpdX2fg7ECS8u6DPbkLI.','0198987654','Delivery','Active',16,20),
(24,'alfredo@hahshop.com','Alfredo','Chocolatery','$2a$10$Li9JomcfpcAIa6SKGhgaUe86ZRyyCIIkJWdIxZJuNp89mbvtaZy6C','0128765424','Seller','Active',17,NULL),
(25,'haaran@gmail.com','Sri ','Haaran','$2a$10$4I88.E6L4DRO9BMyri/H2.DvGBFAp3wIDRFiZ81gRpknHRTxbhRny','0198481723','Customer','Active',18,NULL),
(26,'vans@hahshop.com','Vans','Official Store','$2a$10$WMEU3L9PkwLm0mYfxs8Jmeh1ENXmf/8RRTkFFA000DW4Q.D9112Ie','0123456789','Seller','Active',19,NULL),
(27,'karim@gmail.com','Karim','Benzema','$2a$10$PVjK.N4dp06CweOKFZnSTemEop56eAmkGkPp7Nq8mMFIZX0lS6y8y','012367161','Customer','Active',20,NULL),
(28,'demo',NULL,NULL,'$2a$10$ed5LjbaQDVodX7HbMe079uHPoE/LwC.phq6MAeCYSJtNnWxHUVj9m',NULL,'Admin','Active',NULL,NULL),
(29,'johndoe@gmail.com','John','Doe','$2a$10$OMfcwX3cpVS8CV/gASRcNeYfFohG3lbV7pSkAnXRsQxvxryjooCni','011111111','Customer','Active',21,NULL),
(30,'adidas@hahshop.com','Adidas','Official Store','$2a$10$Dp7vNu4dXdTkb7JnMwvzku6fZmifJl3XuyVm9qzVb78KG1wqKWo6G','012334778','Seller','Active',22,NULL),
(31,'manager@hahshop.com',NULL,NULL,'$2a$10$cva9j.Zg4qPGOIUbg8/TUujlKrmOJ/Ez/icIRBg5BROqobDwtcy/W',NULL,'Admin','Active',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
