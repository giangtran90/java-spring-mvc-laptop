package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadFileService;

@Controller
public class ProductController {
	
	private final ProductService productService;
	private final UploadFileService uploadFileService;

	public ProductController(ProductService productService, UploadFileService uploadFileService) {
		super();
		this.productService = productService;
		this.uploadFileService = uploadFileService;
	}

	@GetMapping("/admin/product")
	public String getProduct(Model model) {
		List<Product> listProducts = productService.fetchAllProduct();
		model.addAttribute("listProducts", listProducts);
		return "admin/product/show";
	}
	
	@GetMapping("/admin/product/create")
	public String getCreateProductPage(Model model) {
		model.addAttribute("newProduct", new Product());
		return "admin/product/create";
	}
	
	@PostMapping("/admin/product/create")
	public String createNewProductPage(Model model
			, @ModelAttribute("newProduct") @Valid Product product
			, BindingResult newUserBindingResult
			, @RequestParam("avatarFile") MultipartFile file) {
		
		List<FieldError> errors = newUserBindingResult.getFieldErrors();
	    for (FieldError error : errors ) {
	        System.out.println (error.getField() + " - " + error.getDefaultMessage());
	    }
		
	    // validate
	    if (newUserBindingResult.hasErrors()) {
	    	return "admin/product/create";
	    }
	    
		String proImg = uploadFileService.handleSaveUploadFile(file, "product");
		product.setImage(proImg);

		productService.handleSaveProduct(product);
		
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/{id}")
	public String getDetailProduct(Model model, @PathVariable Long id) {
		Product product = productService.fetchProductById(id);
		model.addAttribute("productDetail", product);
		return "admin/product/detail";
	}
}
