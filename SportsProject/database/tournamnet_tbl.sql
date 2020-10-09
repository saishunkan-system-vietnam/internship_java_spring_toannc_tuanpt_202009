
DROP TABLE IF EXISTS `tournament_tbl`;

CREATE TABLE `tournament_tbl` (
  `id_tournament` int NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(45) DEFAULT NULL,
  `tournament_status` int DEFAULT NULL,
  `tournament_end` date DEFAULT NULL,
  `tournament_start` date DEFAULT '0000-00-00',
  `tournament_type` varchar(45) DEFAULT NULL,
  `tournament_description` text,
  `tournament_winner` int DEFAULT NULL,
  `tournament_title` text,
  PRIMARY KEY (`id_tournament`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tournament_tbl` WRITE;
INSERT INTO `tournament_tbl` VALUES (118,'Ngoai hang anh',2,'2020-03-17','2020-03-09','Football',NULL,NULL,'Giai nuoc ngoai'),(119,'Bong ban SSV',2,'2020-02-24','2020-02-08','TableTennis',NULL,NULL,'Vui'),(120,'Cup C2',1,'2020-10-17','2020-10-08','Football',NULL,NULL,'Killer'),(122,'Viet Nam',0,'2020-11-20','2020-10-24','Football',NULL,NULL,'áda');
UNLOCK TABLES;

