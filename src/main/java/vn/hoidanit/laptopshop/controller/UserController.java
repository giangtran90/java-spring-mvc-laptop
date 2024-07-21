package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

@Controller
//@RestController
public class UserController {
	
	final private UserService userService;

	private UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/")
	public String getHomePage(Model model) {
		String test = userService.handleHello();
		model.addAttribute("test", test);
		return "test";
	}

//	@GetMapping("/")
//	public String getHomePage() {
//		return userService.handleHello();
//	}
}
