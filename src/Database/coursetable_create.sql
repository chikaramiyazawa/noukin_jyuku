CREATE TABLE `coursetable` (
	`course_id` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`course_name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`detail` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`created` TIMESTAMP NULL DEFAULT NULL,
	`delete_flg` INT(11) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;