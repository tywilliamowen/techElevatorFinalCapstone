package com.techelevator.model.Survey;

import java.util.List;

import com.techelevator.model.Question.Question;

public class Survey {

	private long surveyId;
	private String createdDate;
	private String surveyName;
	private String room;
	private String campus;
	private String cohortNumber;
	private String instructor;
	private String topic;
	private List<Question> surveyList;

	public long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCohortNumber() {
		return cohortNumber;
	}

	public void setCohortNumber(String cohortNumber) {
		this.cohortNumber = cohortNumber;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<Question> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<Question> surveyList) {
		this.surveyList = surveyList;
	}

}
