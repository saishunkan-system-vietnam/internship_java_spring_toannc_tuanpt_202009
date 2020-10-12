
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
INSERT INTO `round_tbl` VALUES (27,'Set 1',1,2,'Team 1 dan trc ti so 1 2 do cau thu a ghi ban',43),
(28,'Set 2',2,3,'Team 2 dan voi ti so 3 2 nang ti so chung cuoc len thanh 3 5',43),
(29,'Set 1',2,3,'acx',44),(30,'Set 2',1,4,'hay',44),(31,'Set 1',1,2,'a',45),
(32,'Set 2',1,2,'a',45),(33,'Set 1',1,2,'a',46),(34,'Set 2',2,3,'a',46),(35,'Set 1',5,3,'a',47),
(36,'Set 2',4,2,'a',47),(37,'Set 1',2,1,'1',48),(38,'Set 2',1,2,'3',48),(39,'Set 3',1,2,'2',48),(40,'Set 1',0,0,NULL,49),
(41,'Set 2',0,0,NULL,49),(42,'Set 3',0,0,NULL,49),(43,'Set 1',0,0,NULL,50),(44,'Set 2',0,0,NULL,50),(45,'Set 3',0,0,NULL,50),
(46,'Set 1',0,0,NULL,51),(47,'Set 2',0,0,NULL,51),(48,'Set 3',0,0,NULL,51),(49,'Set 1',0,0,NULL,52),(50,'Set 2',0,0,NULL,52),
(51,'Set 3',0,0,NULL,52),(52,'Set 1',0,0,NULL,53),(53,'Set 2',0,0,NULL,53),(54,'Set 1',0,0,NULL,54),(55,'Set 2',0,0,NULL,54),
(56,'Set 1',0,0,NULL,55),(57,'Set 2',0,0,NULL,55),(58,'Set 1',0,0,NULL,56),(59,'Set 2',0,0,NULL,56);

UNLOCK TABLES;

