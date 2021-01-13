package com.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signUp";
	}
	
	@PostMapping("/register-success")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		
		return "registerSuccess";
	}
	
	// User List
	@GetMapping("/list")
	public String viewUserList(Model model) {
		List<User> users = repo.findAll();
		model.addAttribute("userList", users);
		
		return "userList";
	}
	
	// Update User
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {

		User user = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("selectedUser", user);
			    
		return "updateUser";
	}
	
	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") int id, User user, 
	  BindingResult result, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
	    if (result.hasErrors()) {
	        user.setId(id);
	        return "updateUser";
	    }
	        
	    repo.save(user);
	    return "redirect:/list";
	}
	
	// Delete User	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    User user = repo.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    repo.delete(user);
	    
	    return "redirect:/list";
	}

}
