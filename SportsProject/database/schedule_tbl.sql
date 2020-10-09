
DROP TABLE IF EXISTS `schedule_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `schedule_tbl` WRITE;

INSERT INTO `schedule_tbl` VALUES (43,'2020-03-08','2020-03-09',39,40,3,5,40,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','Tran dau 1 qua hay . dinh cao','Ha Noi',2,118,'Vong B1','http://localhost:8090/images/download.jpg'),(44,'2020-03-09','2020-03-10',40,44,3,7,44,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','hay','Ha Noi',2,118,'B2','http://localhost:8090/images/c542ed6659be18ce88cb8c7d4aaf8f18o2.jpg'),(45,'2020-03-10','2020-03-11',46,45,2,4,45,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','a','Nam Dinh',2,118,'Vong B1 :b1','http://localhost:8090/images/download.jpg'),(46,'2020-03-11','2020-03-12',45,39,3,5,39,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','1','Da Nang',2,118,'Bang A :cap 2','http://localhost:8090/images/h1.png'),(47,'2020-03-15','2020-03-16',41,40,9,5,40,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','a','Lao',2,118,'Chung ket','http://localhost:8090/images/download.jpg'),(48,'2020-02-08','2020-02-09',43,42,1,2,42,'http://localhost:8090/videos/GacLaiAuLo-DaLAB-6388290.mp4','','Ha Noi',2,119,'Vong 1:cap 1','http://localhost:8090/images/h1.png'),(49,'2020-02-09','2020-02-10',43,42,0,0,NULL,NULL,NULL,'Ha Noi',2,119,'Vong 1:cap 2',NULL),(50,'2020-02-11','2020-02-12',43,48,0,0,NULL,NULL,NULL,'Nam Dinh',2,119,'Vong 2 :cap 3',NULL),(51,'2020-02-13','2020-02-14',47,48,0,0,NULL,NULL,NULL,'HCM city',2,119,'Ban ket 1',NULL),(52,'2020-02-14','2020-02-15',48,42,0,0,NULL,NULL,NULL,'CAn tho',2,119,'Ban ket 2',NULL),(53,'2020-10-08','2020-10-09',39,40,0,0,NULL,NULL,NULL,'Ha noi',0,120,'Ban ket 1',NULL),(54,'2020-10-09','2020-10-10',39,44,0,0,NULL,NULL,NULL,'Ha noi',0,120,'Ban ket 2',NULL),(55,'2020-10-10','2020-10-11',44,39,0,0,NULL,NULL,NULL,'Viet Nam',0,120,'Chung ket',NULL),(56,'2020-10-16','2020-10-23',46,41,0,0,NULL,NULL,NULL,'dsa',0,122,'dsa',NULL);
UNLOCK TABLES;

