SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `moviesactorsdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `moviesactorsdb` ;

DROP TABLE IF EXISTS `moviesactorsdb`.`movies`;
CREATE TABLE `movies` (
	`nid` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(50) NOT NULL,
	`year` INT NOT NULL,
	`duration` INT NOT NULL,
	`country` varchar(50) NOT NULL,
	`synopsis` text,
	`image` varchar(255) ,
    `youtubeid` varchar(50),
	PRIMARY KEY (`nid`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `moviesactorsdb`.`actors`;
CREATE TABLE `actors` (
	`nid` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`dob` DATE NOT NULL,
	`cob` varchar(50),
    `image` varchar(255) ,
    `genre` VARCHAR(1),
	PRIMARY KEY (`nid`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `moviesactorsdb`.`genres`;
CREATE TABLE `genres` (
    `nid` INT NOT NULL AUTO_INCREMENT,
    `description` varchar(50) NOT NULL,
    PRIMARY KEY (`nid`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `moviesactorsdb`.`movies_actors`;
CREATE TABLE `movies_actors` (
	`id_movies_fk` INT NOT NULL,
	`id_actors_fk` INT NOT NULL,
	PRIMARY KEY (`id_movies_fk`,`id_actors_fk`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `moviesactorsdb`.`movies_genres`;
CREATE TABLE `movies_genres` (
    `id_movies_fk` INT NOT NULL,
    `id_genres_fk` INT NOT NULL,
    PRIMARY KEY (`id_movies_fk`,`id_genres_fk`)
) ENGINE = InnoDB;

ALTER TABLE `movies_actors` ADD CONSTRAINT `movies_actors_fk0` FOREIGN KEY (`id_movies_fk`) REFERENCES `movies`(`nid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `movies_actors` ADD CONSTRAINT `movies_actors_fk1` FOREIGN KEY (`id_actors_fk`) REFERENCES `actors`(`nid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `movies_genres` ADD CONSTRAINT `movies_genres_fk2` FOREIGN KEY (`id_genres_fk`) REFERENCES `genres`(`nid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `movies_genres` ADD CONSTRAINT `movies_genres_fk3` FOREIGN KEY (`id_movies_fk`) REFERENCES `movies`(`nid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




