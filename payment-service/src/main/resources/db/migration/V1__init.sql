-- Create the transaction table
CREATE TABLE `transaction` (
  `transactionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `paymentId` bigint(20) DEFAULT NULL,
  `orderId` bigint(20) DEFAULT NULL,
  `transactionDate` datetime DEFAULT NULL,
  `transactionAmount` double DEFAULT NULL,
  `transactionStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Create the user_payment table
CREATE TABLE `user_payment` (
  `paymentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `paymentMethod` varchar(255) DEFAULT NULL,
  `balance` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paymentId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;