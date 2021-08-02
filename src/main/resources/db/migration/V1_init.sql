CREATE TABLE `cat_categoria` (
	`id_categoria` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`descripcion` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`nombre_categoria` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id_categoria`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1;