package com.techelevator.model.Survey;

import java.util.List;

public interface SurveyDAO {

	public List<Survey> getAllSurveys();

	public Survey getSurveyWithId(long id);

	public long getNextSurveyId();

	public void createNewSurvey(String surveyDate, String surveyName, String room, String location, String cohortNumber,
			String instructor, String topic);

	public void deleteExistingSurvey(long id);

	public void updateSurvey(long id, String campus, String cohortNumber, String instructor, String topic);

}
