package com.techelevator.model.Survey;

import org.springframework.web.multipart.MultipartFile;

public class SurveySubmission {

	private String location;
	private String cohortNumber;
	private String instructor;
	private String topic;
	private MultipartFile file;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
