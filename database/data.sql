-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- Insert statements go here

INSERT INTO survey(survey_date, survey_name, room, campus, cohort_number, instructor, topic)
VALUES
('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavab', 'Columbus', '7',  'Brian Lauvray', 'Magnets: How do they work?'),
('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavag', 'Columbus', '7',  'Steve Carmichael', 'Magnets: How do they work?'),
('Thursday, May 24 2018 09:02 AM', 'survey two','tecbusjavab', 'Columbus', '7', 'Brian Lauvray', 'Wide World of Slime'), 
('Thursday, May 24 2018 09:02 AM', 'survey two','tecbusjavag', 'Columbus', '7', 'Steve Carmichael', 'Wide World of Slime'), 
('Friday, May 25 2018 09:02 AM', 'survey three','tecbusjavab', 'Columbus', '7', 'Brian Lauvray', 'All About Dust Bunnies'),  
('Friday, May 25 2018 09:02 AM', 'survey three','tecbusjavag', 'Columbus', '7', 'Steve Carmichael', 'All About Dust Bunnies'); 


INSERT INTO answer(question_id, answer_text, student_id, survey_id)
VALUES
('1', 'true', 'STUDENT1', '1'), 
('1', 'true', 'STUDENT2', '1'), 
('2', 'just right', 'STUDENT1', '1'), 
('2', 'a little too fast', 'STUDENT2', '1'), 
('3', 'facinating', 'STUDENT1', '1'), 
('3', 'boring', 'STUDENT2', '1'), 
('4', 'excellent, I could teach it', 'STUDENT1', '1'), 
('4', 'good but needs a little more practice', 'STUDENT2', '1'), 
('5', '10', 'STUDENT1', '1'), 
('5', '9', 'STUDENT2', '1'), 

('1', 'true', 'STUDENT1', '2'), 
('1', 'true', 'STUDENT2', '2'), 
('2', 'just right', 'STUDENT1', '2'), 
('2', 'a little too fast', 'STUDENT2', '2'), 
('3', 'facinating', 'STUDENT1', '2'), 
('3', 'boring', 'STUDENT2', '2'), 
('4', 'excellent, I could teach it', 'STUDENT1', '2'), 
('4', 'good but needs a little more practice', 'STUDENT2', '2'), 
('5', '10', 'STUDENT1', '2'), 
('5', '9', 'STUDENT2', '2'), 

('1', 'true', 'STUDENT1', '3'), 
('1', 'true', 'STUDENT2', '3'), 
('2', 'just right', 'STUDENT1', '3'), 
('2', 'a little too fast', 'STUDENT2', '3'), 
('3', 'facinating', 'STUDENT1', '3'), 
('3', 'boring', 'STUDENT2', '3'), 
('4', 'excellent, I could teach it', 'STUDENT1', '3'), 
('4', 'good but needs a little more practice', 'STUDENT2', '3'), 
('5', '10', 'STUDENT1', '3'), 
('5', '9', 'STUDENT2', '3'),

('1', 'true', 'STUDENT1', '4'), 
('1', 'true', 'STUDENT2', '4'), 
('2', 'just right', 'STUDENT1', '4'), 
('2', 'a little too fast', 'STUDENT2', '4'), 
('3', 'facinating', 'STUDENT1', '4'), 
('3', 'boring', 'STUDENT2', '4'), 
('4', 'excellent, I could teach it', 'STUDENT1', '4'), 
('4', 'good but needs a little more practice', 'STUDENT2', '4'), 
('5', '10', 'STUDENT1', '4'), 
('5', '9', 'STUDENT2', '4'),

('1', 'true', 'STUDENT1', '5'), 
('1', 'true', 'STUDENT2', '5'), 
('2', 'just right', 'STUDENT1', '5'), 
('2', 'a little too fast', 'STUDENT2', '5'), 
('3', 'facinating', 'STUDENT1', '5'), 
('3', 'boring', 'STUDENT2', '5'), 
('4', 'excellent, I could teach it', 'STUDENT1', '5'), 
('4', 'good but needs a little more practice', 'STUDENT2', '5'), 
('5', '10', 'STUDENT1', '5'), 
('5', '9', 'STUDENT2', '5'),

('1', 'true', 'STUDENT1', '6'), 
('1', 'true', 'STUDENT2', '6'), 
('2', 'just right', 'STUDENT1', '6'), 
('2', 'a little too fast', 'STUDENT2', '6'), 
('3', 'facinating', 'STUDENT1', '6'), 
('3', 'boring', 'STUDENT2', '6'), 
('4', 'excellent, I could teach it', 'STUDENT1', '6'), 
('4', 'good but needs a little more practice', 'STUDENT2', '6'), 
('5', '10', 'STUDENT1', '6'), 
('5', '9', 'STUDENT2', '6');

INSERT INTO question(question_text)
VALUES
('Presence'), 
('The pace of yesterday''s class was:'), 
('The content of the previous class was:'),
('I feel my level of understanding of the previous day''s material is:'),
('On a scale of 1-10, my energy level today is:');

INSERT INTO survey_question(question_id, survey_id) 
VALUES
(1,1), 
(2,1),
(3,1),
(4,1),
(5,1),

(1,2), 
(2,2),
(3,2),
(4,2),
(5,2),

(1,3), 
(2,3),
(3,3),
(4,3),
(5,3),

(1,4), 
(2,4),
(3,4),
(4,4),
(5,4),

(1,5), 
(2,5),
(3,5),
(4,5),
(5,5),

(1,6), 
(2,6),
(3,6),
(4,6),
(5,6);

INSERT INTO student(student_id, student_name)
VALUES
('STUDENT1', 'Keys, Bobby'), 
('STUDENT2', 'Hopkins, Nicky');


INSERT INTO app_user(user_name, password, role, salt, temporary_password) 
VALUES
('userTemp', '4YbAeLajCoyT/3miTru9bQ==', 'User', 'Zj75Cs1H4p1k6HBrY7dGfPLeivQ9Kzt1GJPiUIZVjDXFqlpUC5MDSwif34CFfh+eIX16IFxBAUdwRNNamMqG5KXx7jZEXOIDF48PI2hjdoonxUSt0Rw3Fl/5BGIR1H8wJysSmgu9cSYjWWXMxriEsYML82tqrKDI5d792yXs+hk=', true),
('admin', '4YbAeLajCoyT/3miTru9bQ==', 'Admin', 'Zj75Cs1H4p1k6HBrY7dGfPLeivQ9Kzt1GJPiUIZVjDXFqlpUC5MDSwif34CFfh+eIX16IFxBAUdwRNNamMqG5KXx7jZEXOIDF48PI2hjdoonxUSt0Rw3Fl/5BGIR1H8wJysSmgu9cSYjWWXMxriEsYML82tqrKDI5d792yXs+hk=', false);

COMMIT;