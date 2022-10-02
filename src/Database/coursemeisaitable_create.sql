CREATE TABLE `coursemeisaitable` (
	`course_id` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`grade` INT(11) NULL DEFAULT NULL,
	`subject` INT(11) NULL DEFAULT NULL,
	`tuition` BIGINT(20) NULL DEFAULT NULL,
	`start_lecture` DATETIME NULL DEFAULT NULL,
	`end_lecture` DATETIME NULL DEFAULT NULL,
	`day_lecture` INT(11) NULL DEFAULT NULL,
	`teacher` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`delete_flg` INT(11) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
