package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class HomePageController {

	private final ProductService productService;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	// All product
	@GetMapping("/")
	public String getHomePage(Model model, HttpServletRequest request) {
		List<Product> products = this.productService.fetchAllProduct();
		model.addAttribute("listProducts", products);
		
		HttpSession session = request.getSession(false);
		
		return "client/homepage/show";
	}
	
	// Get Register page
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerUser", new RegisterDTO());
		return "client/auth/register";
	}
	
	// Create a Register
	@PostMapping("/register")
	public String createAcount(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
		
		// validate
	    if (bindingResult.hasErrors()) {
	    	return "client/auth/register";
	    }
		
		User user = this.userService.RegisterDTOtoUser(registerDTO);
		String hashPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		user.setRole(this.userService.fetchRoleByName("USER"));
		// save
		userService.handleSaveUser(user);
		return "redirect:/login";
	}
	
	// Get Login page
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		return "client/auth/login";
	}
	
	// Get Access Deny page (403)
	@GetMapping("/access-deny")
	public String getAccessDenyPage(Model model) {
		return "client/auth/deny";
	}
	
}
