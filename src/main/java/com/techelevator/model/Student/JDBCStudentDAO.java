package com.techelevator.model.Student;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Student.StudentDAO;

@Component
public class JDBCStudentDAO implements StudentDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCStudentDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public void insertStudentIntoTableIfStudentDoesNotExits(String studentId, String studentName) {
		String sql = "Select Count(*) from student WHERE student_id LIKE (?)";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, studentId);

		result.next();
		if (result.getLong(1) == 0) {
			String sqlInsert = "INSERT INTO student (student_id, student_name) VALUES (?, ?)";
			jdbcTemplate.update(sqlInsert, studentId, studentName);
		}
	}

}
