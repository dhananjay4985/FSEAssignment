package user.library.librarymanagementusingbasicauth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.library.librarymanagementusingbasicauth.model.User;
import user.library.librarymanagementusingbasicauth.service.UserService;

@RestController
@RequestMapping(value="/library")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@GetMapping("/checkvaliduser/")
	public boolean checkValidUser(@RequestHeader(value="email") String email,@RequestHeader(value="password") String password) {
		return userService.userLogin(email, password);
	}
	
	@PostMapping("/allusers")
	public void addUser(@Valid @RequestBody User user) {
		System.out.println("inside addUser Request");
		userService.addUser(user);
	}
}
