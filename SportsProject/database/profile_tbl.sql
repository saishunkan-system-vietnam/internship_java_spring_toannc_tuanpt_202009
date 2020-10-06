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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci