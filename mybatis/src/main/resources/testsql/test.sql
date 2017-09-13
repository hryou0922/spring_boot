CREATE TABLE `test`.`test` (
  `id` INT NOT NULL,
  `age` INT NULL,
  `name` VARCHAR(45) NULL,
  `create` TIMESTAMP(6) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
