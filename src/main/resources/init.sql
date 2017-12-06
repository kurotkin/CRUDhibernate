CREATE DATABASE IF NOT EXISTS HibernateCRUD;
USE HibernateCRUD;

DROP TABLE IF EXISTS developer_projects;
DROP TABLE IF EXISTS developer_skill;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS developers;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS companies;

CREATE TABLE IF NOT EXISTS skills(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) not NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS projects(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) not NULL,
  cost decimal not null,
  company_id int,
  customer_id int
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS companies(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) not NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS developers(
  id int PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(50) not null,
  last_name varchar(100) not null,
  specialty varchar(100) not null,
  salary decimal not null
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS customers(
  id int PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(50) not null,
  last_name varchar(100) not null
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS developer_skill(
  developer_id int not null,
  skill_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (skill_id) REFERENCES skills(id),

  UNIQUE (developer_id, skill_id),
  PRIMARY KEY (developer_id, skill_id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS developer_projects(
  developer_id int not null,
  projects_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (projects_id) REFERENCES projects(id),

  UNIQUE (developer_id, projects_id),
  PRIMARY KEY (developer_id, projects_id)
) ENGINE = InnoDB;


