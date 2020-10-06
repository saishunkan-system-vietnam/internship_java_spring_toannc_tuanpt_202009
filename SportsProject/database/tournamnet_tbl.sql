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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci