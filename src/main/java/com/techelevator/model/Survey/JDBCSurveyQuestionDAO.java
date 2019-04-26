package com.techelevator.model.Survey;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyQuestionDAO implements SurveyQuestionDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyQuestionDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public void createNewRow(long questionId, long surveyId) {
		String sql = "Insert INTO survey_question (question_id, survey_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, questionId, surveyId);
	}

}
