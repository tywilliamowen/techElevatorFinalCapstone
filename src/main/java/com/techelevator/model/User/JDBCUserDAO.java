package com.techelevator.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.User.UserDAO;
import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}

	@Override
	public void saveUser(String userName, String password, String role) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));

		jdbcTemplate.update(
				"INSERT INTO app_user(user_name, password, salt, role, temporary_password) VALUES (?, ?, ?, ?, true)",
				userName, hashedPassword, saltString, role);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * " + "FROM app_user " + "WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if (user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {

		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));

		jdbcTemplate.update(
				"UPDATE app_user SET temporary_password = false, password = ?, salt = ? WHERE user_name = ?",
				hashedPassword, saltString, userName);
	}

	@Override
	public void updatePasswordTemporary(long id, String password) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));

		jdbcTemplate.update("UPDATE app_user SET temporary_password = true, password = ?, salt = ?  WHERE id = ?",
				hashedPassword, saltString, id);

	}

	@Override
	public boolean isTemporaryPassword(long id) {
		String sql = "select temporary_password from app_user where id =?";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sql, id);
		boolean tempPasswordReturn = true;
		if (user.next()) {
			tempPasswordReturn = user.getBoolean(1);
		}

		return tempPasswordReturn;
	}

	@Override
	public void updateRole(String role, long id) {
		jdbcTemplate.update("UPDATE app_user SET role = ? WHERE id = ?", role, id);
	}

	@Override
	public void deleteUser(long id) {
		jdbcTemplate.update("DELETE from app_user WHERE id = ?", id);
	}

	@Override
	public Object getUserByUserName(String userName) {
		String sqlSearchForUsername = "SELECT * " + "FROM app_user " + "WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase());
		User thisUser = null;
		if (user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setRole(user.getString("role"));
			thisUser.setUserNameId(user.getLong("id"));
		}

		return thisUser;
	}

	@Override
	public List<User> getAllUsers() {

		String sql = "SELECT * FROM app_user";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

		List<User> users = new ArrayList<User>();
		while (result.next()) {
			users.add(mapRowToUser(result));
		}

		return users;
	}

	private User mapRowToUser(SqlRowSet result) {

		User user = new User();

		user.setUserName(result.getString("user_name"));
		user.setRole(result.getString("role"));
		user.setUserNameId(result.getLong("id"));

		return user;
	}

}
