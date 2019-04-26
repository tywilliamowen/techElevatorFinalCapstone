package com.techelevator.model.Question;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCQuestionDAO implements QuestionDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCQuestionDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Question> getQuestionsBySurveyId(long id) {
		String sql = "SELECT question.question_id, question.question_text from survey_question join question on question.question_id = survey_question.question_id where survey_id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

		List<Question> questions = new ArrayList<Question>();
		while (result.next()) {
			questions.add(mapRowToQuestion(result));
		}
		return questions;
	}

	private Question mapRowToQuestion(SqlRowSet result) {

		Question question = new Question();
		question.setQuestionId(result.getLong("question_id"));
		question.setQuestionText(result.getString("question_text"));

		return question;
	}

	@Override
	public long getQuestionIdByQuestionText(String questionText) {
		String sql = "select question_id from question where question_text LIKE ?";
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet(sql, questionText);
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new survey");
		}
	}

}
