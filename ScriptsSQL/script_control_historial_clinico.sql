-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control_historial_clinico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema control_historial_clinico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `control_historial_clinico` DEFAULT CHARACTER SET utf8 ;
USE `control_historial_clinico` ;

-- -----------------------------------------------------
-- Table `control_historial_clinico`.`exploracion_fisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_historial_clinico`.`exploracion_fisica` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `fecha_exploracion` DATE NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_historial_clinico`.`diagnostico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_historial_clinico`.`diagnostico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `sistema_id` INT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `fecha_diagnostico` DATE NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`, `sistema_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_historial_clinico`.`revaloracion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_historial_clinico`.`revaloracion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `sistema_id` INT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `fecha_revaloracion` DATE NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`, `sistema_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_historial_clinico`.`pronostico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_historial_clinico`.`pronostico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `fecha_pronostico` DATE NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_historial_clinico`.`estudios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_historial_clinico`.`estudios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `paciente_id` INT NOT NULL,
  `url` VARCHAR(245) NOT NULL,
  PRIMARY KEY (`id`, `paciente_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
