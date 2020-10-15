
DROP TABLE IF EXISTS `round_tbl`;

CREATE TABLE `round_tbl` (
  `id_round` int NOT NULL AUTO_INCREMENT,
  `round_name` text,
  `round_score1` int DEFAULT '0',
  `round_score2` int DEFAULT '0',
  `round_description` text,
  `round_schedule` int DEFAULT NULL,
  PRIMARY KEY (`id_round`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `round_tbl` VALUES (60,'Set 1',0,0,NULL,57),(61,'Set 2',0,0,NULL,57),(62,'Set 1',0,0,NULL,58),(63,'Set 2',0,0,NULL,58),(64,'Set 1',0,0,NULL,59),(65,'Set 2',0,0,NULL,59),(66,'Set 1',1,2,'Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n',60),(67,'Set 2',4,1,'Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n',60),(68,'Set 1',1,2,'Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n',61),(69,'Set 2',2,0,'Reece James (England) probably spoke out of turn to the referee and is sent off for dissent.\n',61),(70,'Set 1',0,0,NULL,62),(71,'Set 2',0,0,NULL,62),(72,'Set 1',3,4,'World Cup',63),(73,'Set 2',1,2,'World Cup',63),(74,'Set 1',0,0,NULL,64),(75,'Set 2',0,0,NULL,64),(76,'Set 1',0,0,NULL,65),(77,'Set 2',0,0,NULL,65),(78,'Set 1',0,0,NULL,66),(79,'Set 2',0,0,NULL,66),(80,'Set 1',0,0,NULL,67),(81,'Set 2',0,0,NULL,67);


UNLOCK TABLES;

