-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control_citas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema control_citas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `control_citas` DEFAULT CHARACTER SET utf8 ;
USE `control_citas` ;

-- -----------------------------------------------------
-- Table `control_citas`.`citas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_citas`.`citas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `fecha_hora` TIMESTAMP NOT NULL,
  `tipo_paciente` VARCHAR(45) NOT NULL,
  `estatus_cita` VARCHAR(45) NOT NULL,
  `no_sesion` INT NOT NULL,
  `costo_terapia` DECIMAL(14,2) NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
