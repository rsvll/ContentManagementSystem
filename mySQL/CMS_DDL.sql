DROP DATABASE IF EXISTS `CMS`;
CREATE DATABASE `CMS`;
USE `CMS`;

CREATE TABLE IF NOT EXISTS `User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `userEmail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `bio` VARCHAR(45) NOT NULL,
  `isAdmin`TINYINT(1) NOT NULL,
  `enabled`TINYINT(1) NOT NULL,
  PRIMARY KEY (`idUser`),
  KEY(`userName`));
  
CREATE TABLE IF NOT EXISTS `Authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`));

CREATE TABLE IF NOT EXISTS `Categories` (
  `idCategories` INT NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(45) NOT NULL,
  `categoryDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategories`));


CREATE TABLE IF NOT EXISTS `BlogPost` (
  `idBlogPost` INT NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `description` LONGTEXT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `createdDate` DATETIME NOT NULL,
  `publishDate` VARCHAR(45) NOT NULL,
  `expirationDate` DATETIME NULL,
  `approved` TINYINT NOT NULL,
  `idUser` INT NOT NULL,
  `idCategories` INT NOT NULL,
  PRIMARY KEY (`idBlogPost`),
  CONSTRAINT `fk_BlogPost_User`
    FOREIGN KEY (`idUser`)
    REFERENCES `User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_BlogPost_Categories`
    FOREIGN KEY (`idCategories`)
    REFERENCES `Categories` (`idCategories`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `StaticPage` (
  `idStaticPage` INT NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `description` LONGTEXT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `dateCreated` DATETIME NOT NULL,
  `publishedDate` DATETIME NOT NULL,
  `expirationDate` DATETIME NOT NULL,
  `isActive` BOOLEAN NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idStaticPage`),
  CONSTRAINT `fk_StaticPage_User`
    FOREIGN KEY (`idUser`)
    REFERENCES `User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `Tag` (
  `idTag` INT NOT NULL AUTO_INCREMENT,
  `tagName` VARCHAR(45) NOT NULL,
  `tagDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTag`));

CREATE TABLE IF NOT EXISTS `BlogpostTag` (
  `idBlogPost` INT NOT NULL,
  `idTag` INT NOT NULL,
  PRIMARY KEY (`idBlogPost`, `idTag`),
  CONSTRAINT `fk_BlogpostTag_BlogPost`
    FOREIGN KEY (`idBlogPost`)
    REFERENCES `BlogPost` (`idBlogPost`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_BlogpostTag_Tag`
    FOREIGN KEY (`idTag`)
    REFERENCES `Tag` (`idTag`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `Authorities`
 ADD CONSTRAINT `Authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `User` (`username`);
