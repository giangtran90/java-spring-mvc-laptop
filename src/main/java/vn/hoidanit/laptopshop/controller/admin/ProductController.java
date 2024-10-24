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
	
	// get page create
	@GetMapping("/admin/product/create")
	public String getCreateProductPage(Model model) {
		model.addAttribute("newProduct", new Product());
		return "admin/product/create";
	}
	
	// create new product
	@PostMapping("/admin/product/create")
	public String createNewProductPage(Model model
			, @ModelAttribute("newProduct") @Valid Product product
			, BindingResult newProductBindingResult
			, @RequestParam("avatarFile") MultipartFile file) {
		
		List<FieldError> errors = newProductBindingResult.getFieldErrors();
	    for (FieldError error : errors ) {
	        System.out.println (error.getField() + " - " + error.getDefaultMessage());
	    }
		
	    // validate
	    if (newProductBindingResult.hasErrors()) {
	    	return "admin/product/create";
	    }
	    
		String proImg = uploadFileService.handleSaveUploadFile(file, "product");
		product.setImage(proImg);

		productService.handleSaveProduct(product);
		
		return "redirect:/admin/product";
	}
	
	// get view page
	@GetMapping("/admin/product/{id}")
	public String getDetailProduct(Model model, @PathVariable Long id) {
		Product product = productService.fetchProductById(id);
		model.addAttribute("productDetail", product);
		return "admin/product/detail";
	}
	
	// get update page
	@GetMapping("/admin/product/update/{id}")
	public String getUpdateProductPage(Model model,  @PathVariable Long id) {
		Product product = productService.fetchProductById(id);
		model.addAttribute("updateProduct", product);
		return "admin/product/update";
	}
	
	// update product
	@PostMapping("/admin/product/update")
	public String updateProduct(Model model
			, @ModelAttribute("updateProduct") @Valid Product product
			, BindingResult updateProBindingResult
			, @RequestParam("avatarFile") MultipartFile file) {
		
		List<FieldError> errors = updateProBindingResult.getFieldErrors();
	    for (FieldError error : errors ) {
	        System.out.println (error.getField() + " - " + error.getDefaultMessage());
	    }
		
	    // validate
	    if (updateProBindingResult.hasErrors()) {
	    	return "admin/product/update";
	    }
		
	    Product currentPro = productService.fetchProductById(product.getId());
	    String image = uploadFileService.handleSaveUploadFile(file, "product");
	    if (currentPro != null) {
	    	currentPro.setPrice(product.getPrice());
	    	currentPro.setDetailDesc(product.getDetailDesc());
	    	currentPro.setShortDesc(product.getShortDesc());
	    	currentPro.setQuantity(product.getQuantity());
	    	if (!"".equals(image)) {
	    		currentPro.setImage(image);
	    	}
	    	productService.handleSaveProduct(currentPro);
	    }
		return "redirect:/admin/product";
	}
	
	// Get delete page
	@GetMapping("/admin/product/delete/{id}")
	public String getDeleteProductPage(Model model, @PathVariable Long id) {
		Product product = productService.fetchProductById(id);
		model.addAttribute("deleteProduct", product);
		return "admin/product/delete";
	}
	
	// Delete Product
	@PostMapping("/admin/product/delete")
	public String deleteProduct(Model model, @ModelAttribute("deleteProduct") Product product) {
		productService.deleteProduct(product.getId());
		return "redirect:/admin/product";
	}
}
