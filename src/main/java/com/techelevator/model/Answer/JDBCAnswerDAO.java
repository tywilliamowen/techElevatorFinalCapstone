package com.techelevator.model.Answer;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCAnswerDAO implements AnswerDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCAnswerDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Answer> getAnswersBySurveyIdAndQuestionId(long surveyId, long question_id) {
		String sql = "SELECT answer_id, student_id, answer_text from ANSWER where survey_id = ? AND question_id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, surveyId, question_id);

		List<Answer> answers = new ArrayList<Answer>();
		while (result.next()) {
			answers.add(mapRowToAnswer(result));
		}

		return answers;
	}

	private Answer mapRowToAnswer(SqlRowSet result) {
		Answer answer = new Answer();

		answer.setAnswerId(result.getLong("answer_id"));
		answer.setStudentId(result.getString("student_id"));
		answer.setAnswerText(result.getString("answer_text"));

		return answer;
	}

	private Answer mapRowToAnswerWithJoin(SqlRowSet result) {
		Answer answer = new Answer();

		answer.setAnswerText(result.getString("answer_text"));
		answer.setStudentName(result.getString("student_name"));
		answer.setAnswerId(result.getLong("answer_id"));
		answer.setStudentName(result.getString("student_name"));

		return answer;
	}

	@Override
	public void updateAnswerText(String answerText, long answerId) {
		String sql = " UPDATE answer SET answer_text = ? WHERE answer_id = ?";
		jdbcTemplate.update(sql, answerText, answerId);
	}

	@Override
	public void createNewAnswer(long questionId, String answerText, String studentId, long surveyId) {
		String sql = "Insert INTO answer (question_id, answer_text, student_id, survey_id) Values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, questionId, answerText, studentId, surveyId);
	}

	@Override
	public List<Answer> getStudentNameAndAnswerBySurveyIdAndQuestionId(long surveyId, long question_id) {

		String sql = "Select  student.student_id, answer.answer_id, student.student_name, answer.answer_text from student join answer on student.student_id = answer.student_id WHERE answer.survey_id = ? and answer.question_id = ? order by student.student_name ASC";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, surveyId, question_id);

		List<Answer> answers = new ArrayList<Answer>();
		while (result.next()) {
			answers.add(mapRowToAnswerWithJoin(result));
		}

		return answers;
	}

}
