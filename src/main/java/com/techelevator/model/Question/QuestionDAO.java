package com.techelevator.model.Question;

import java.util.List;

public interface QuestionDAO {

	public List<Question> getQuestionsBySurveyId(long id);

	public long getQuestionIdByQuestionText(String questionText);

}
