package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Answer.AnswerStats;
import com.techelevator.model.Answer.JDBCAnswerStatsDAO;

public class JDBCAnswerStatsDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCAnswerStatsDAO AnswerStatsDAO;
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
		AnswerStatsDAO = new JDBCAnswerStatsDAO(getDataSource());
	}
	
	@Test
	public void getCountAndTextOfUniqueAnswersForSurveyQuestion_returns_correct_size_list() {
		
		List<AnswerStats> testList = AnswerStatsDAO.getCountAndTextOfUniqueAnswersForSurveyQuestion(1, 1);
		
		Assert.assertEquals(1, testList.size());
	}
	
	@Test
	public void getCountAndTextOfUniqueAnswersForSurvey_returns_the_correct_size_list() {
		
		List<AnswerStats> testListTwo = AnswerStatsDAO.getCountAndTextOfUniqueAnswersForSurvey(1);
		
		Assert.assertEquals(9, testListTwo.size());
	}

}
