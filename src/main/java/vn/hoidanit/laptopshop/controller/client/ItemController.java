package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class ItemController {

	private final ProductService productService;
	
	public ItemController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/product/{id}")
	public String getProductDetail(Model model, @PathVariable long id) {
		Product product = this.productService.fetchProductById(id);
		model.addAttribute("product", product);
		return "client/product/detail";
	}
	
	@PostMapping("/add-product-to-cart/{id}")
	public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// check xem sản phẩm đã exist trong cart hay chưa
		String email = (String) session.getAttribute("email");
		this.productService.handleAddProductToCart(id, email);
		
		return "redirect:/";
	}
	
	@GetMapping("/cart")
	public String getCart() {
		return "client/cart/cart";
	}
}
