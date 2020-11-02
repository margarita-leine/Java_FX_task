CREATE TABLE IF NOT EXISTS `order_list`(
`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
`customer` VARCHAR(100),
`product_list` VARCHAR(1000),
`order_date` DATE,
`order_value` DOUBLE,
`registration_date` DATE,
`payment_date` DATE,
`sent_date` DATE);
