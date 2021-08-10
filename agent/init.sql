USE `products`;
INSERT INTO `product` (`id`,`name`, `price`, `quantity`, `picture`) VALUES ('1', 'blabla', '2000', '10', 'picture');


SET SQL_SAFE_UPDATES = 0;
UPDATE `hibernate_sequence` SET `next_val` = '6' WHERE `next_val` = '1';