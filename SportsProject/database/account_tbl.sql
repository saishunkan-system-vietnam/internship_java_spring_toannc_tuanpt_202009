CREATE TABLE `account_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_username` varchar(45) NOT NULL,
  `account_email` varchar(45) NOT NULL,
  `account_password` text NOT NULL,
  `account_role` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;
INSERT INTO`sports`.`account_tbl` (`account_username`, `account_email`, `account_password`, `account_role`) VALUES ('admin', 'admin@gmail.com', '$2a$12$ZbhE55fzVkhoLgVk71THuOt6vZbfQsSuRb2zZvRHtU.q5rNHZQXDi', 'ROLE_ADMIN');
