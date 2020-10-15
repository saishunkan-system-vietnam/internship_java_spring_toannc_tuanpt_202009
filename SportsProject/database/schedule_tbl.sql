
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

INSERT INTO `schedule_tbl` VALUES (57,'2018-10-16','2019-10-17',49,50,3,5,50,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'League A','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(58,'2018-10-17','2019-10-18',52,53,3,6,53,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'Legague A','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(59,'2018-10-18','2019-10-20',49,53,5,7,53,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'Quarterfinals','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(60,'2018-10-22','2019-10-23',52,50,5,3,50,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'Quarterfinals','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(61,'2018-11-18','2019-11-19',49,53,3,2,53,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'Final','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(62,'2018-11-27','2019-11-28',52,50,3,5,50,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n','Stadion Maksimir ',2,123,'Third-place','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(63,'2020-10-12','2020-10-13',49,50,4,6,50,'http://localhost:8090/videos/Tilt_shift_ftbol_soccer.mov','World Cup','World Cup',2,124,'Round 1','http://localhost:8090/images/0ea1b05d6ff1bcdff7d0b2bbc4ffb4dfo2.jpg'),(64,'2020-10-13','2020-10-14',49,52,0,0,NULL,NULL,NULL,'World Cup',2,124,'Round 2',NULL),(65,'2020-10-14','2020-10-19',52,53,0,0,NULL,NULL,NULL,'World Cup',1,124,'Round 1',NULL),(66,'2020-10-20','2020-10-21',53,50,0,0,NULL,NULL,NULL,'World Cup',0,124,'Round 2',NULL),(67,'2020-10-23','2020-10-24',49,53,0,0,NULL,NULL,NULL,'World Cup',0,124,'Final',NULL);
UNLOCK TABLES;

