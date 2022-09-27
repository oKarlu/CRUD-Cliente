-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projetoetb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projetoetb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projetoetb` DEFAULT CHARACTER SET utf8 ;
USE `projetoetb` ;

-- -----------------------------------------------------
-- Table `projetoetb`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoetb`.`menu` (
  `idMenu` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(64) NOT NULL,
  `link` VARCHAR(128) NOT NULL,
  `icone` VARCHAR(128) NULL,
  `exibir` INT NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`idMenu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoetb`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoetb`.`perfil` (
  `idPerfil` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `dataCadastro` DATE NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`idPerfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoetb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoetb`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(128) NOT NULL,
  `login` VARCHAR(24) NOT NULL,
  `senha` VARCHAR(128) NOT NULL,
  `status` INT NOT NULL,
  `idPerfil` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_usuario_perfil_idx` (`idPerfil` ASC),
  CONSTRAINT `fk_usuario_perfil`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `projetoetb`.`perfil` (`idPerfil`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoetb`.`menu_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoetb`.`menu_perfil` (
  `idMenu` INT NOT NULL,
  `idPerfil` INT NOT NULL,
  PRIMARY KEY (`idMenu`, `idPerfil`),
  INDEX `fk_menu_has_perfil_perfil1_idx` (`idPerfil` ASC) ,
  INDEX `fk_menu_has_perfil_menu1_idx` (`idMenu` ASC) ,
  CONSTRAINT `fk_menu_has_perfil_menu1`
    FOREIGN KEY (`idMenu`)
    REFERENCES `projetoetb`.`menu` (`idMenu`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_has_perfil_perfil1`
    FOREIGN KEY (`idPerfil`)
    REFERENCES `projetoetb`.`perfil` (`idPerfil`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projetoetb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projetoetb`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(128) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `endereco` VARCHAR(128) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `dataCadastro` DATE NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

