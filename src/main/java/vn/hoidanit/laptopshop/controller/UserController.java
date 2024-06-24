package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

//@Controller
@RestController
public class UserController {

//	@RequestMapping("/")
//	public String getHomePage() {
//		return "Hello Controller";
//	}
	final private UserService userService;

	private UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/")
	public String getHomePage() {
		return userService.handleHello();
	}
}
