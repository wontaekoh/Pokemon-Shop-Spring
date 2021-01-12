package com.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

	// Connecting to JdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean create(User user) {
		String sql="INSERT INTO users (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
		
		return true;
	}
	
	public boolean update(User user) {
		String sql="UPDATE users SET firstname=?, lastname=?, email=? WHERE id=?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
		
		return true;
	}
	
	public boolean delete(Long id) {
		String sql="DELETE FROM users WHERE id=?";
		jdbcTemplate.update(sql, id);
		
		return true;
	}
	
	public List<User> read() {
		String sql="SELECT * FROM users";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper());
		
		return list;
	}
}
