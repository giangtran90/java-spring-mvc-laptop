package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class ItemController {

	private final ProductService productService;
	
	public ItemController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/products/{id}")
	public String getProductDetail(Model model, @PathVariable long id) {
		Product product = this.productService.fetchProductById(id);
		model.addAttribute("product", product);
		return "/client/product/detail";
	}
}
