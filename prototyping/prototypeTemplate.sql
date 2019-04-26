INSERT INTO survey(survey_date, room, survey_name)
VALUES
('Wednesday, May 23 2018 09:02 AM', 'tecbusjavab', 'survey one'),
('Wednesday, May 23 2018 09:02 AM', 'tecbusjavag', 'survey one'), 
('Thursday, May 24 2018 09:02 AM', 'tecbusjavab', 'survey two'), 
('Thursday, May 24 2018 09:02 AM', 'tecbusjavag', 'survey two'),
('Friday, May 25 2018 09:02 AM', 'tecbusjavab', 'survey three'), 
('Friday, May 25 2018 09:02 AM', 'tecbusjavag', 'survey three');


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

INSERT INTO student(student_id, student_first_name, student_last_name)
VALUES
('STUDENT1', 'Bobby', 'Keys'), 
('STUDENT2', 'Nicky', 'Hopkins');


SELECT question.question_id, question.question_text from survey_question join question on question.question_id = survey_question.question_id where survey_id = ? ; 


SELECT answer_id, student_id, answer_text from ANSWER where survey_id = ? AND question_id = ? 


Select * from student join answer on answer.student_id = student.student_id join question on question.question_id = answer.question_id where question.question_id = ? and answer.survey_id = ?; 


 