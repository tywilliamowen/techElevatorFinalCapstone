package com.techelevator.model.Answer;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCAnswerStatsDAO implements AnswerStatsDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCAnswerStatsDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<AnswerStats> getCountAndTextOfUniqueAnswersForSurveyQuestion(long questionId, long surveyId) {
		String sql = "select count (answer_text) ,answer_text  from answer where question_id  = ? AND survey_id = ? group by answer_text order by count desc";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, questionId, surveyId);

		List<AnswerStats> answerStats = new ArrayList<AnswerStats>();
		while (result.next()) {
			answerStats.add(mapRowToAnswerStatsQuestion(result));
		}

		return answerStats;
	}

	@Override
	public List<AnswerStats> getCountAndTextOfUniqueAnswersForSurvey(long surveyId) {
		String sql = "select question_id, count (answer_text),  answer_text from answer where survey_id = ? group by answer_text, question_id order by question_id, count desc";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, surveyId);

		List<AnswerStats> answerStats = new ArrayList<AnswerStats>();
		while (result.next()) {
			answerStats.add(mapRowToAnswerStatsSurvey(result));
		}

		return answerStats;
	}

	private AnswerStats mapRowToAnswerStatsQuestion(SqlRowSet result) {
		AnswerStats answerStats = new AnswerStats();

		answerStats.setAnswerText(result.getString("answer_text"));
		answerStats.setCountOfAnswers(result.getLong("count"));

		return answerStats;
	}

	private AnswerStats mapRowToAnswerStatsSurvey(SqlRowSet result) {
		AnswerStats answerStats = new AnswerStats();

		answerStats.setAnswerText(result.getString("answer_text"));
		answerStats.setCountOfAnswers(result.getLong("count"));
		answerStats.setQuestionId(result.getLong("question_id"));

		return answerStats;
	}

}
