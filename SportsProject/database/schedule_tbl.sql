CREATE TABLE `schedule_tbl` (
  `id_schedule` int NOT NULL AUTO_INCREMENT,
  `schedule_start` date DEFAULT NULL,
  `schedule_end` date DEFAULT NULL,
  `schedule_team1` int DEFAULT NULL,
  `schedule_team2` int DEFAULT NULL,
  `schedule_score1` int DEFAULT '0',
  `schedule_score2` int DEFAULT '0',
  `schedule_winner` int DEFAULT NULL,
  `schedule_video` text,
  `schedule_description` text,
  `schedule_address` text,
  `schedule_status` int DEFAULT '0',
  `schedule_tour` int DEFAULT NULL,
  `schedule_title` text,
  `schedule_image` text,
  PRIMARY KEY (`id_schedule`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci