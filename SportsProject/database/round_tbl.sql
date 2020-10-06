CREATE TABLE `round_tbl` (
  `id_round` int NOT NULL AUTO_INCREMENT,
  `round_name` text,
  `round_score1` int DEFAULT '0',
  `round_score2` int DEFAULT '0',
  `round_description` text,
  `round_schedule` int DEFAULT NULL,
  PRIMARY KEY (`id_round`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci