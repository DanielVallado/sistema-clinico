-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema control_pacientes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema control_pacientes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `control_pacientes` DEFAULT CHARACTER SET utf8 ;
USE `control_pacientes` ;

-- -----------------------------------------------------
-- Table `control_pacientes`.`antecedentes_heredo_familiares`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`antecedentes_heredo_familiares` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cardiopatias` TINYINT NOT NULL,
  `alergias` TINYINT NOT NULL,
  `diabetes` TINYINT NOT NULL,
  `nefropatias` TINYINT NOT NULL,
  `psiquiatricos` TINYINT NOT NULL,
  `neurologicas` TINYINT NOT NULL,
  `otros_sindromes` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`antecedentes_personales_no_patologicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`antecedentes_personales_no_patologicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `habitos_personales` VARCHAR(255) NULL,
  `baño` VARCHAR(45) NULL,
  `habitacion` VARCHAR(45) NULL,
  `tabaquismo` TINYINT NULL,
  `alcoholismo` TINYINT NULL,
  `vacunas` VARCHAR(255) NULL,
  `actividad_fisica` VARCHAR(45) NULL,
  `alimentacion` VARCHAR(255) NULL,
  `estado_civil` VARCHAR(45) NULL,
  `zoonosis` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`antecedentes_personales_patologicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`antecedentes_personales_patologicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cirugias` VARCHAR(255) NULL,
  `adicciones` VARCHAR(255) NULL,
  `traumatismos` VARCHAR(255) NULL,
  `ets` VARCHAR(255) NULL,
  `alergias` VARCHAR(255) NULL,
  `padecimientos_articulares` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`antecedentes_gineco_obstetricos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`antecedentes_gineco_obstetricos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `menarca` DATE NULL,
  `ritmo_menstrual` VARCHAR(45) NULL,
  `ipsa` INT NULL,
  `partos` INT NULL,
  `fup` DATE NULL,
  `abortos` INT NULL,
  `cesareas` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`antecedentes_perinatales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`antecedentes_perinatales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_nacimiento` DATE NULL,
  `sdg` INT NULL,
  `apgar` INT NULL,
  `peso` FLOAT NULL,
  `talla` FLOAT NULL,
  `numero_embarazo` INT NULL,
  `problemas_durante_parto` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`padecimiento_actual`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`padecimiento_actual` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inicio` DATE NOT NULL,
  `descripcion` TEXT NOT NULL,
  `evolucion` TEXT NULL,
  `estado_actual` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `control_pacientes`.`pacientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `control_pacientes`.`pacientes` (
  `no_expediente` INT NOT NULL AUTO_INCREMENT,
  `creacion_expediente` DATE NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(20) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `estado_civil` VARCHAR(45) NOT NULL,
  `escolaridad` VARCHAR(100) NOT NULL,
  `ocupacion` VARCHAR(100) NOT NULL,
  `antecedentes_personales_patologicos_id` INT NOT NULL,
  `antecedentes_heredo_familiares_id` INT NOT NULL,
  `antecedentes_personales_no_patologicos_id` INT NOT NULL,
  `antecedentes_gineco_obstetricos_id` INT NULL,
  `antecedentes_perinatales_id` INT NULL,
  `padecimiento_actual_id` INT NOT NULL,
  PRIMARY KEY (`no_expediente`, `antecedentes_personales_patologicos_id`, `antecedentes_heredo_familiares_id`, `antecedentes_personales_no_patologicos_id`, `padecimiento_actual_id`),
  INDEX `fk_pacientes_antecedentes_personales_patologicos_idx` (`antecedentes_personales_patologicos_id` ASC) VISIBLE,
  INDEX `fk_pacientes_antecedentes_heredo_familiares1_idx` (`antecedentes_heredo_familiares_id` ASC) VISIBLE,
  INDEX `fk_pacientes_antecedentes_personales_no_patologicos1_idx` (`antecedentes_personales_no_patologicos_id` ASC) VISIBLE,
  INDEX `fk_pacientes_antecedentes_perinatales1_idx` (`antecedentes_perinatales_id` ASC) VISIBLE,
  INDEX `fk_pacientes_padecimiento_actual1_idx` (`padecimiento_actual_id` ASC) VISIBLE,
  INDEX `fk_pacientes_antecedentes_gineco_obstetricos1_idx` (`antecedentes_gineco_obstetricos_id` ASC) VISIBLE,
  CONSTRAINT `fk_pacientes_antecedentes_personales_patologicos`
    FOREIGN KEY (`antecedentes_personales_patologicos_id`)
    REFERENCES `control_pacientes`.`antecedentes_personales_patologicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacientes_antecedentes_heredo_familiares1`
    FOREIGN KEY (`antecedentes_heredo_familiares_id`)
    REFERENCES `control_pacientes`.`antecedentes_heredo_familiares` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacientes_antecedentes_personales_no_patologicos1`
    FOREIGN KEY (`antecedentes_personales_no_patologicos_id`)
    REFERENCES `control_pacientes`.`antecedentes_personales_no_patologicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacientes_antecedentes_perinatales1`
    FOREIGN KEY (`antecedentes_perinatales_id`)
    REFERENCES `control_pacientes`.`antecedentes_perinatales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacientes_padecimiento_actual1`
    FOREIGN KEY (`padecimiento_actual_id`)
    REFERENCES `control_pacientes`.`padecimiento_actual` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pacientes_antecedentes_gineco_obstetricos1`
    FOREIGN KEY (`antecedentes_gineco_obstetricos_id`)
    REFERENCES `control_pacientes`.`antecedentes_gineco_obstetricos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
