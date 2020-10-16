
DROP TABLE IF EXISTS `team_tbl`;

CREATE TABLE `team_tbl` (
  `id_team` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(45) DEFAULT NULL,
  `team_type` varchar(45) DEFAULT NULL,
  `team_description` text,
  `team_logo` text,
  `id_tour` int DEFAULT '0',
  PRIMARY KEY (`id_team`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `team_tbl` WRITE;
INSERT INTO `team_tbl` VALUES (49,'England','Football','Đội tuyển bóng đá quốc gia Anh là đội bóng lâu đời trên thế giới, được thành lập cùng một lúc với đội tuyển bóng đá Scotland','http://localhost:8090/images/tCG9KQYA-84VqVvfA.png',124),(50,'Denmark','Football','Đội tuyển bóng đá quốc gia Đan Mạch, còn có biệt danh là \"Thùng thuốc nổ Đan Mạch\", là đội tuyển của Hiệp hội bóng đá Đan Mạch và đại diện cho Đan Mạch trên bình diện quốc tế.','http://localhost:8090/images/d8MRvRBr-vs3rrxd7.png',124),(52,'Belgium','Football','Đội tuyển bóng đá quốc gia Bỉ là đội tuyển cấp quốc gia của Bỉ do Hiệp hội bóng đá Hoàng gia Bỉ quản lý','http://localhost:8090/images/Qur1J5jl-ALa3qyER.png',124),(53,'Luxembourg','Football','Đội tuyển bóng đá quốc gia Luxembourg là đội tuyển cấp quốc gia của Luxembourg do Liên đoàn bóng đá Luxembourg quản lý','http://localhost:8090/images/6Ja9JTjl-rcUSadb8.png',124);
UNLOCK TABLES;

