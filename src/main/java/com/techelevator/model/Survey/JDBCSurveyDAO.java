package com.techelevator.model.Survey;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Survey> getAllSurveys() {

		String sql = "SELECT * FROM survey";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

		List<Survey> surveys = new ArrayList<Survey>();
		while (result.next()) {
			surveys.add(mapRowToSurvey(result));
		}

		return surveys;
	}

	@Override
	public Survey getSurveyWithId(long id) {

		String sql = "SELECT survey_id, survey_date, survey_name, room, campus, cohort_number, instructor, topic FROM survey WHERE survey_id = ? ";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		result.next();
		Survey survey = mapRowToSurvey(result);

		return survey;
	}

	@Override
	public void createNewSurvey(String surveyDate, String surveyName, String room, String location, String cohortNumber,
			String instructor, String topic) {
		String sql = "Insert INTO survey (survey_date, survey_name, room, campus, cohort_number, instructor, topic) Values (?, ?, ?, ?, ?, ?, ? )";
		jdbcTemplate.update(sql, surveyDate, surveyName, room, location, cohortNumber, instructor, topic);
	}

	@Override
	public long getNextSurveyId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('survey_survey_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new survey");
		}
	}

	@Override
	public void deleteExistingSurvey(long id) {

		String deleteFromSurveyQuestion = "DELETE from survey_question where survey_id = ?";
		String deleteFromSurvey = "DELETE from survey where survey_id = ?";
		String deleteFromAnswer = "DELETE from answer where survey_id = ?";

		jdbcTemplate.update(deleteFromSurveyQuestion, id);
		jdbcTemplate.update(deleteFromSurvey, id);
		jdbcTemplate.update(deleteFromAnswer, id);
	}

	@Override
	public void updateSurvey(long id, String campus, String cohortNumber, String instructor, String topic) {
		String sql = "UPDATE survey SET campus = ?, cohort_number = ? , instructor = ? , topic = ? WHERE survey_id = ?";
		jdbcTemplate.update(sql, campus, cohortNumber, instructor, topic, id);
	}

	private Survey mapRowToSurvey(SqlRowSet result) {

		Survey survey = new Survey();

		survey.setSurveyId(result.getLong("survey_id"));
		survey.setCreatedDate(result.getString("survey_date"));
		survey.setSurveyName(result.getString("survey_name"));
		survey.setRoom(result.getString("room"));
		survey.setCampus(result.getString("campus"));
		survey.setCohortNumber(result.getString("cohort_number"));
		survey.setInstructor(result.getString("instructor"));
		survey.setTopic(result.getString("topic"));

		return survey;
	}

}
