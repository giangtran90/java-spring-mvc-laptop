package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {

	private final ProductService productService;
	
	public HomePageController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		List<Product> products = this.productService.fetchAllProduct();
		model.addAttribute("listProducts", products);
		return "/client/homepage/show";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerUser", new RegisterDTO());
		return "/client/auth/register";
	}
	
	@PostMapping("/register")
	public String createAcount(@ModelAttribute("registerUser") RegisterDTO acount) {
		return "redirect:/client/auth/register";
	}
}
