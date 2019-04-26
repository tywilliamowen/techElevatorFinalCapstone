package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Survey.JDBCSurveyDAO;
import com.techelevator.model.Survey.Survey;

public class JDBCSurveyDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCSurveyDAO SurveyDAO;
	private JdbcTemplate jdbcTemplate;
	Integer id;
	
	@Before
	public void setup() {
		String truncateSql = "TRUNCATE survey_question, survey";
		String sqlInsertSurvey = "INSERT INTO survey(survey_date, survey_name, room, campus, cohort_number, instructor, topic) VALUES ('Wednesday, May 23 2018 09:02 AM', 'survey one', 'tecbusjavab', 'Columbus', '7',  'Brian Lauvray', 'Magnets: How do they work?') Returning survey_id";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(truncateSql);
		id = jdbcTemplate.queryForObject(sqlInsertSurvey, Integer.class);
		SurveyDAO = new JDBCSurveyDAO(getDataSource());
	}
	
	@Test
	public void getAllSurveys_returns_a_populated_list() {
		List<Survey> testList = SurveyDAO.getAllSurveys();
		
		Assert.assertEquals(1, testList.size());
	}
	
	@Test
	public void getSurveyWithId_returns_the_correct_survey() {
		Survey survey = SurveyDAO.getSurveyWithId(id);
		
		Assert.assertEquals("Wednesday, May 23 2018 09:02 AM", survey.getCreatedDate());
		Assert.assertEquals("survey one", survey.getSurveyName());
		Assert.assertEquals("tecbusjavab", survey.getRoom());
		Assert.assertEquals("Columbus", survey.getCampus());
		Assert.assertEquals("7", survey.getCohortNumber());
		Assert.assertEquals("Brian Lauvray", survey.getInstructor());
		Assert.assertEquals("Magnets: How do they work?", survey.getTopic());
	}
	
}
