package com.techelevator.model.Answer;

public class AnswerStats {

	private long countOfAnswers;
	private String answerText;
	private long questionId;

	public long getCountOfAnswers() {
		return countOfAnswers;
	}

	public void setCountOfAnswers(long countOfAnswers) {
		this.countOfAnswers = countOfAnswers;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

}
