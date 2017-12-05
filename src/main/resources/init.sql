CREATE DATABASE IF NOT EXISTS HibernateCRUD;
USE HibernateCRUD;

CREATE TABLE IF NOT EXISTS skills(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL
);

CREATE TABLE IF NOT EXISTS projects(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL,
  cost decimal not null,
  company_id int,
  customer_id int
);

CREATE TABLE IF NOT EXISTS companies(
  id int PRIMARY KEY,
  name VARCHAR(100) not NULL
);

CREATE TABLE IF NOT EXISTS developers(
  id int PRIMARY KEY,
  first_name varchar(50) not null,
  last_name varchar(100) not null,
  specialty varchar(100) not null,
  salary decimal not null
);

CREATE TABLE IF NOT EXISTS customers(
  id int PRIMARY KEY,
  first_name varchar(50) not null,
  last_name varchar(100) not null
);

CREATE TABLE IF NOT EXISTS developer_skill(
  developer_id int not null,
  skill_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (skill_id) REFERENCES skills(id),

  UNIQUE (developer_id, skill_id)
);

CREATE TABLE IF NOT EXISTS developer_projects(
  developer_id int not null,
  projects_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (projects_id) REFERENCES projects(id),

  UNIQUE (developer_id, projects_id)
);


