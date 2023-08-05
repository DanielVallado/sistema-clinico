-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control_sistemas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema control_sistemas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `control_sistemas` DEFAULT CHARACTER SET utf8 ;
USE `control_sistemas` ;

-- -----------------------------------------------------
-- Table `control_sistemas`.`sistemas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_sistemas`.`sistemas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_sistemas`.`aparatos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_sistemas`.`aparatos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TEXT NOT NULL,
  `sistema_id` INT NOT NULL,
  PRIMARY KEY (`id`, `sistema_id`),
  INDEX `fk_aparatos_sistemas_idx` (`sistema_id` ASC) VISIBLE,
  CONSTRAINT `fk_aparatos_sistemas`
    FOREIGN KEY (`sistema_id`)
    REFERENCES `control_sistemas`.`sistemas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
