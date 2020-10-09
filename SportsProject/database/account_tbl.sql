
DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE `account_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_username` varchar(45) NOT NULL,
  `account_email` varchar(45) NOT NULL,
  `account_password` text NOT NULL,
  `account_role` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `account_tbl` WRITE;
INSERT INTO `account_tbl` VALUES (23,'admin','admin@gmail.com','$2a$12$ZbhE55fzVkhoLgVk71THuOt6vZbfQsSuRb2zZvRHtU.q5rNHZQXDi','ROLE_ADMIN'),
(24,'nguyen trung kien586100','kien@gmail.com','$2a$12$9EbFHbwixC.042TC6WDsMuW8OtjzNwDrMKpATYCtLBUNDRcdqocnS','ROLE_MEMBER'),
(25,'nguyen ba quat66100','kieaaan@gmail.com','$2a$12$yduEk41DrDaINogTtssmj.FRSXH7yIOWFoGlsRdI7h4DLCSh6fqRq','ROLE_MEMBER'),
(26,'nguyen duc hung540100','hung@gmail.com','$2a$12$hEFGVDSdxQD0lHu4j6tGAONPmupVqI985GVE6b3j6EGZ10TkqbWJO','ROLE_MEMBER'),
(27,'toan539100','toan@gmail','$2a$12$0CctyWS3KE6gCD40A01jJ.0nQ0bbM7lQxe2m6MshEF0yzKa9X98Ry','ROLE_MEMBER'),
(28,'toan1226100','toan1@gmail','$2a$12$4.nd/11f6YGJisRdeuw4NusqLju3anK2YrX2oTWz8HEvyww.w32L.','ROLE_MEMBER'),
(29,'toan2441100','toan2@gmail','$2a$12$ytF9fTSkox2q3utgVWA/G.rPuEiUUq9zZqiGmp3Kxqgg3.pH2BbOy','ROLE_MEMBER'),
(30,'toan372100','toan3@gmail','$2a$12$CohSec.dp3wW6TJbJ1xXheAhXdph0BgVg.p8z/5kc3VMFAYhN79eS','ROLE_MEMBER'),
(31,'toan4828100','toan4@gmail','$2a$12$MHyRM8BphYD5YBy2MbC3buOdhQgFS12cbPDcn/ZMfqalmZnhQNaNa','ROLE_MEMBER'),
(32,'toan5116100','toan5@gmail','$2a$12$yoyLD0.n3vLsP7lPg8U3VOwwaUwGfEJ8ZoB/grhjCc75.2UxIAmB6','ROLE_MEMBER'),
(33,'toan6508100','toan6@gmail','$2a$12$LTLXEzYj/eGT7uO247gpJO4uM1az/SEBJNAqwIyn/Cv6Ksbdt2geK','ROLE_MEMBER'),
(34,'toan7717100','toan7@gmail','$2a$12$B1GfcsRGp0sN3YJr63xKueKz5rxoO9m8dh2TiPEBbYX2zbg.WvQn2','ROLE_MEMBER'),
(35,'toan8405100','toan8@gmail','$2a$12$WNwy72T7IqiE2DrSZSoUUuorGik1HpKWHz9qGEHFdFn4uErvAEB9W','ROLE_MEMBER'),
(36,'toan9791100','toan9@gmail','$2a$12$iGdjnN2JFxI34av5wae/teBW2nP6WfQnNQlTYD1DFG4uDmX1c9lTm','ROLE_MEMBER'),
(37,'toan10148100','toan10@gmail','$2a$12$UwpzGf5QZ2mPaKxW.8ewL.wck3/FEWZIlY0Ia0cxaFcgWCqcojiYG','ROLE_MEMBER'),
(38,'toan11542100','toan11@gmail','$2a$12$RUteGrmgtJrs36NCathyyeJq4e3r5kw2l3DXO2Qz8C8keF5k1NcrO','ROLE_MEMBER'),
(39,'tuan1661100','tuan@gmail.com','$2a$12$h3DtL8KcV66MN.fi.6XE/ezfsLPgR6kDQyTJIWXkdFEjRSz.7cbve','ROLE_MEMBER'),
(40,'tuan2284100','tuan2@gmail.com','$2a$12$iQ/JFahy2vy/CrwOol88HOEWVfTGcBIFmhPNX1UDuiJ2pKkWmM.xK','ROLE_MEMBER'),
(41,'tuan321100','tuan3@gmail.com','$2a$12$kZuVBGmerspNMMeOFdW.Hu3gItxp2jkT97R6krTmJqtRwiTkLm5x6','ROLE_MEMBER'),
(42,'tuan4169100','tuan4@gmail.com','$2a$12$egD0SMTkOO1/eGEJoLrzQO/.hflxbtKScnxI40T2wJeP1CjbLNOX6','ROLE_MEMBER'),
(43,'tuan5164100','tuan5@gmail.com','$2a$12$/zY8bFB5hV4yVGP7yxYROu7zA9aCFr5WqM7046wOvD3n/vDk/.IjS','ROLE_MEMBER'),
(44,'tuan630100','tuan6@gmail.com','$2a$12$dsAPfi1Wsm7f0y6XB2um2.sp/UnUzRBoTNpxPlQOss2VN.aDEc2HO','ROLE_MEMBER'),
(45,'tuan7403100','tuan7@gmail.com','$2a$12$0Z8ttM9kF99I725jofSmgu.oUkjFcgGsUPuLhE7QoDjehLJ71zohG','ROLE_MEMBER'),
(46,'tuan8299100','tuan8@gmail.com','$2a$12$slcMrNXZ6q56hVHU8Hws2OlS2FGbTC3/2KUXz6uY2QG2x055N.c92','ROLE_MEMBER'),
(47,'tuan931100','tuan9@gmail.com','$2a$12$dykR6h52yzc.MSkHKzSLEey9PhSk.Y.F9rpithvh7nLAtCjQtj1Bm','ROLE_MEMBER'),
(48,'tuan1045100','tuan10@gmail.com','$2a$12$0KuewzMZYmS.iR9Q5TXtpenXV9rJSlBPvZmJNRx650fdU1dHZjqdq','ROLE_MEMBER'),
(49,'kun791100','a@gmail.com','$2a$12$a/lCNdKxPNqNpbxlaU85gOjsDV3RF8wodA8VN9CrEJ2m3ujPpu89u','ROLE_MEMBER'),
(50,'kun1587100','a1@gmail.com','$2a$12$W0zuVZs5tW5nPEmy0eGHx.AtdszDZDbCPjBke0aAUvHRRYaTIUA2K','ROLE_MEMBER');
UNLOCK TABLES;

