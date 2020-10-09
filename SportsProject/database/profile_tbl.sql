

DROP TABLE IF EXISTS `profile_tbl`;
CREATE TABLE `profile_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `profile_name` varchar(45) DEFAULT NULL,
  `profile_email` varchar(45) DEFAULT NULL,
  `profile_phone` varchar(45) DEFAULT NULL,
  `profile_age` varchar(45) DEFAULT NULL,
  `profile_gender` varchar(45) DEFAULT NULL,
  `profile_address` text,
  `profile_avatar` text,
  `id_team` int DEFAULT NULL,
  `profile_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `profile_tbl` WRITE;
INSERT INTO `profile_tbl` VALUES (13,'admin','admin@gmail.com','0981784335','12','nam','Ha Noi','http://localhost:8090/images/h1.png',NULL,'Football'),
(14,'Nguyen Trung Kien','kien@gmail.com','1234564798','12','Male','Ha Noi','http://localhost:8090/images/download.jpg',39,'Football'),
(15,'Nguyen Ba Quat','kieaaan@gmail.com','1234564798','13','Male','Ha Noi','http://localhost:8090/images/download.jpg',39,'Football'),
(16,'Nguyen Duc hung','hung@gmail.com','1234564798','45','Male','Ha Noi','http://localhost:8090/images/download.jpg',39,'Football'),
(17,'toan','toan@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',40,'Football'),
(18,'toan1','toan1@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',40,'Football'),
(19,'toan2','toan2@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',44,'Football'),
(20,'toan3','toan3@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',44,'Football'),
(21,'toan4','toan4@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',46,'Football'),
(22,'toan5','toan5@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',46,'Football'),
(23,'toan6','toan6@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',41,'Football'),
(24,'toan7','toan7@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',41,'Football'),
(25,'toan8','toan8@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',41,'Football'),
(26,'toan9','toan9@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',43,'TableTennis'),
(27,'toan10','toan10@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',42,'TableTennis'),
(28,'toan11','toan11@gmail','123','32','Male','Ha Noi','http://localhost:8090/images/download.jpg',43,'TableTennis'),
(29,'tuan1','tuan@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',42,'TableTennis'),
(30,'tuan2','tuan2@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',48,'TableTennis'),
(31,'tuan3','tuan3@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',48,'TableTennis'),
(32,'tuan4','tuan4@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',47,'TableTennis'),
(33,'tuan5','tuan5@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',47,'TableTennis'),
(34,'tuan6','tuan6@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',47,'TableTennis'),
(35,'tuan7','tuan7@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',47,'TableTennis'),
(36,'tuan8','tuan8@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,'TableTennis'),
(37,'tuan9','tuan9@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,'TableTennis'),
(38,'tuan10','tuan10@gmail.com','112','12','Male','Ha Noi','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,'TableTennis'),
(39,'Kun','a@gmail.com','123','12','Male','ấ','http://localhost:8090/images/download.jpg',45,NULL),
(40,'Kun1','a1@gmail.com','123','12','Male','ấ','http://localhost:8090/images/download.jpg',45,NULL);
UNLOCK TABLES;
