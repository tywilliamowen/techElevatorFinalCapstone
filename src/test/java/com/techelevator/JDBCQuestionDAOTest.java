package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Answer.JDBCAnswerDAO;
import com.techelevator.model.Question.Question;
import com.techelevator.model.Question.JDBCQuestionDAO;

public class JDBCQuestionDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCQuestionDAO QuestionDAO;
	private JdbcTemplate jdbcTemplate;
	Integer id;
	
	@Before
	public void setup() {
		
			
			String sqlInsertQuestion = "INSERT INTO question(question_text)\n" + 
					"VALUES\n" + 
					"('Presence'), \n" + 
					"('The pace of yesterday''s class was:'), \n" + 
					"('The content of the previous class was:'),\n" + 
					"('I feel my level of understanding of the previous day''s material is:'),\n" + 
					"('On a scale of 1-10, my energy level today is:')";
			String sqlInsertSurvey = "INSERT INTO survey(survey_date, survey_name, room, campus, cohort_number, instructor, topic) VALUES ('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavab', 'Columbus', '7',  'Brian Lauvray', 'Magnets: How do they work?')";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
			jdbcTemplate.update(sqlInsertQuestion);
			jdbcTemplate.update(sqlInsertSurvey);
			
			QuestionDAO = new JDBCQuestionDAO(getDataSource());
	}
	
	@Test
	public void getQuestionsBySurveyId_returns_the_correct_list_size(){
		
		List<Question> testList = QuestionDAO.getQuestionsBySurveyId(1);
		
		Assert.assertEquals(5, testList.size());
	}
	
	@Test
	public void getQuestionIdByQuestionText_returns_the_correct_id() {
		
		long testId = QuestionDAO.getQuestionIdByQuestionText("Presence");
		
		Assert.assertEquals(1, testId);
		
		
	}
	

}
