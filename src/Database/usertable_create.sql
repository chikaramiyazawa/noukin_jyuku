CREATE TABLE `usertable` (
	`USER_ID` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`USER_NAME` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`USER_PASS` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`ADMIN` INT(11) NULL DEFAULT NULL,
	`TUITION` BIGINT(20) NULL DEFAULT NULL,
	`DELETE_FLG` INT(11) NULL DEFAULT NULL,
	`UP` TIMESTAMP NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;