
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
INSERT INTO `tournament_tbl` VALUES (123,'UEFA Nations League',2,'2019-01-30','2018-10-15','Football',NULL,50,'Giải vô địch bóng đá các quốc gia châu âu 2019'),(124,'World Cup',1,'2021-01-01','2020-09-01','Football',NULL,NULL,'Super ');
UNLOCK TABLES;

