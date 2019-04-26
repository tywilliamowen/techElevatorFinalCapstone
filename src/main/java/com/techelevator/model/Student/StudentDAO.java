package com.techelevator.model.Student;

public interface StudentDAO {

	public void insertStudentIntoTableIfStudentDoesNotExits(String studentId, String studentName);

}
