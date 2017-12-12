package com.hillel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private long   id;
    private String author;
    private String country;
    private String language;
    private String link;
    private int    pages;
    private String title;
    private int    year;
    private byte[] image;
    private User   user;

}
/*
CREATE TABLE `bookstore`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `language` VARCHAR(45) NULL,
  `link` VARCHAR(45) NULL,
  `pages` INT NULL,
  `title` VARCHAR(45) NULL,
  `year` INT NULL,
  `image` BLOB NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;



CREATE TABLE `bookstore`.`user` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `login` VARCHAR(45) NULL,
  `encoded_password` VARCHAR(150) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

 */