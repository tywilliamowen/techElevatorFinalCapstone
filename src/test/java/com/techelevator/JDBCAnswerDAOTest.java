package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Answer.Answer;
import com.techelevator.model.Answer.JDBCAnswerDAO;



public class JDBCAnswerDAOTest extends DAOIntegrationTest {

	@Autowired
	private JDBCAnswerDAO AnswerDAO;
	private JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void setup() {
		
		String sqlInsertSurvey = "TRUNCATE survey_question, survey, answer; INSERT INTO survey(survey_date, survey_name, room, campus, cohort_number, instructor, topic) VALUES ('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavab', 'Columbus', '7',  'Brian Lauvray', 'Magnets: How do they work?')";
		String sqlInsertAnswer = "INSERT INTO answer(question_id, answer_text, student_id, survey_id)\n" + 
				"VALUES\n" + 
				"('1', 'true', 'STUDENT1', '1'), \n" + 
				"('1', 'true', 'STUDENT2', '1'), \n" + 
				"('2', 'just right', 'STUDENT1', '1'), \n" + 
				"('2', 'a little too fast', 'STUDENT2', '1'), \n" + 
				"('3', 'facinating', 'STUDENT1', '1'), \n" + 
				"('3', 'boring', 'STUDENT2', '1'), \n" + 
				"('4', 'excellent, I could teach it', 'STUDENT1', '1'), \n" + 
				"('4', 'good but needs a little more practice', 'STUDENT2', '1'), \n" + 
				"('5', '10', 'STUDENT1', '1'), \n" + 
				"('5', '9', 'STUDENT2', '1')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sqlInsertSurvey);
		jdbcTemplate.update(sqlInsertAnswer);
		AnswerDAO = new JDBCAnswerDAO(getDataSource());
		
		
	}
	
	@Test
	public void getAnswersBySurveyIdAndQuestionId_returns_the_correct_number_of_answers() {
		
		
		List<Answer> test = AnswerDAO.getAnswersBySurveyIdAndQuestionId(1, 1);
		
		
		Assert.assertEquals(2, test.size());
	}
	
	@Test
	public void getStudentNameAndAnswerBySurveyIdAndQuestionId_returns_the_correct_size() {
		
		List<Answer> testList = AnswerDAO.getStudentNameAndAnswerBySurveyIdAndQuestionId(1, 1);
		
		Assert.assertEquals(2, testList.size());
	}
}
