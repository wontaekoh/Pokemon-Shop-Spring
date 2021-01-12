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
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ProductRepository prodrepo;
	
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
	
	// Product List
	@GetMapping("/product-list")
	public String viewProductList(Model model) {
		List<Products> products = prodrepo.findAll();
		model.addAttribute("prodList", products);
		
		return "productList";
	}
	
	// Update User
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {

		User user = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("selectedUser", user);
			    
		return "updateUser";
	}
	
	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") long id, User user, 
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
	public String deleteUser(@PathVariable("id") long id, Model model) {
	    User user = repo.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    repo.delete(user);
	    
	    return "redirect:/list";
	}
	
	
	// read
	
	
}
