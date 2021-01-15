package com.shop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
// Using in-memory DB in default, but we want to use real DB to create table
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	/**
	 * Test case for creating user
	 */
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setFirstName("tom");
		user.setLastName("test");
		user.setEmail("tom@email.com");
		user.setPassword("tom123");
		
		User savedUser = repo.save(user);
		
		User existingUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	/**
	 * Test case for reading users from database
	 */
	@Test
	public void testReadUser() {
		List<User> users = repo.findAll();
		
		System.out.println(users);
		
		assertThat(users.size()).isNotNull();
	}
	
	/**
	 * Test case for updating user info and encrypted password
	 */
	@Test
	public void testUpdateUser() {
		String email = "tom@email.com";
		
		User user = repo.findByEmail(email);
		
		// Updating user info
		user.setFirstName("Tom");
		user.setLastName("Brown");
		user.setEmail("tom.brown@email.com");
		user.setPassword("tombrown");
		
		// Encrypt updated password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		User updatedUser = repo.save(user);
		
		User existingUser = entityManager.find(User.class, updatedUser.getId());
		
		assertThat(existingUser.getFirstName()).isEqualTo(user.getFirstName());
		assertThat(existingUser.getLastName()).isEqualTo(user.getLastName());
		assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	/**
	 * Test case for deleting user
	 */
	@Test
	public void testDeleteUser() {
		int id = 8;
		
		repo.deleteById(id);

		assertThat(repo.findById(id));
	}
	
	/**
	 * Test case for finding user by its email
	 */
	@Test
	public void testFindUserByEmail() {
		String email = "test@email.com";
		
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	/**
	 * Test case for finding user by its id
	 */
	@Test
	public void testFindUserByID() {
		int id = 17;
				
		Optional<User> user = repo.findById(id);
		System.out.println(user);
		
		assertThat(user).isNotNull();
	}

}
