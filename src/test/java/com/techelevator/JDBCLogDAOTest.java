package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Log.JDBCLogDAO;
import com.techelevator.model.Log.Log;

public class JDBCLogDAOTest extends DAOIntegrationTest {
	
	@Autowired
	private JDBCLogDAO LogDAO;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		String sqlInsertLog = "TRUNCATE Log; INSERT INTO log(editing_user, log_text) VALUES ('Admin', 'test text')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sqlInsertLog);
		
		LogDAO = new JDBCLogDAO(getDataSource());
	}
	
	@Test
	public void getAllLogs_returns_populated_list() {
		List<Log> test = LogDAO.getAllLogs();
		
		Assert.assertEquals(1, test.size());
	}

	
}
