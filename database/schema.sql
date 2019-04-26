-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;
drop table if exists survey_question;
DROP TABLE IF EXISTS app_user; 
drop table if exists survey; 
drop table if exists answer;
drop table if exists question; 
drop table if exists student; 
drop table if exists log; 


-- Create Statements Go Here


CREATE TABLE log (
 log_event serial PRIMARY KEY, 
 editing_user text not null, 
 log_text text not null, 
 event_date TIMESTAMP default current_timestamp
);

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL,
  temporary_password boolean
);

create table survey (
survey_id serial PRIMARY KEY, 
survey_date text NOT NULL,
survey_name text NOT NULL,
room text NOT NULL,
campus text NOT NULL, 
cohort_number text NOT NULL,  
instructor text NOT NULL, 
topic  text NOT NULL
); 

create table answer
(answer_id serial PRIMARY KEY, 
question_id int NOT NULL, 
answer_text text, 
student_id text NOT NULL, 
survey_id int NOT NULL
); 

create table question
(question_id serial PRIMARY KEY, 
question_text text NOT NULL 
); 

create table student
(student_id text PRIMARY KEY, 
student_name text NOT NULL 
); 

create table survey_question(
  question_id int NOT NULL,
  survey_id int NOT NULL,
  constraint fk_survey_question_question_id foreign key (question_id) references question(question_id),
  constraint fk_survey_question_survey_id foreign key (survey_id) references survey(survey_id)
);



COMMIT;