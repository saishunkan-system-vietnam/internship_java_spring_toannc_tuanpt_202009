
DROP TABLE IF EXISTS `team_tbl`;

CREATE TABLE `team_tbl` (
  `id_team` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(45) DEFAULT NULL,
  `team_totalmatch` int DEFAULT NULL,
  `team_type` varchar(45) DEFAULT NULL,
  `team_description` text,
  `team_totalwin` int DEFAULT NULL,
  `team_logo` text,
  `team_totalmember` int DEFAULT NULL,
  `id_tour` int DEFAULT '0',
  PRIMARY KEY (`id_team`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `team_tbl` WRITE;
INSERT INTO `team_tbl` VALUES (39,'Manchester',0,'Football','Vo dich',NULL,'http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,120),(40,'Man City',0,'Football','Quỷ xanh',NULL,'http://localhost:8090/images/download.jpg',NULL,120),(41,'VietNam',1,'Football','Win',NULL,'http://localhost:8090/images/h1.png',NULL,122),(42,'China',0,'TableTennis','Lucian',NULL,'http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,0),(43,'Austraylia',0,'TableTennis','bacba',NULL,'http://localhost:8090/images/download.jpg',NULL,0),(44,'Mong Co',0,'Football','Dũng mãnh',NULL,'http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,120),(45,'Nami',0,'Football','Abc',NULL,'http://localhost:8090/images/h1.png',NULL,122),(46,'Ugost',1,'Football','xyz\n',NULL,'http://localhost:8090/images/h1.png',NULL,122),(47,'Phamtomassasi',0,'TableTennis','Dota2',NULL,'http://localhost:8090/images/download.jpg',NULL,0),(48,'Jpan',0,'TableTennis','abc',NULL,'http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg',NULL,0);
UNLOCK TABLES;

