package com.techelevator.model.Answer;

import java.util.List;

public interface AnswerStatsDAO {

	public List<AnswerStats> getCountAndTextOfUniqueAnswersForSurveyQuestion(long questionId, long surveyId);

	public List<AnswerStats> getCountAndTextOfUniqueAnswersForSurvey(long surveyId);

}
