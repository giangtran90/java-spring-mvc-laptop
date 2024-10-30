package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
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
		this.productService.handleAddProductToCart(id, email, session);
		
		return "redirect:/";
	}
	
	@GetMapping("/cart")
	public String getCart(Model model, HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		String email = (String) session.getAttribute("email");
//		List<CartDetail> listCartDetails = this.productService.fetchCartDetailByUser(email);
//		model.addAttribute("cartDetails", listCartDetails);
//		double total = 0;
//		for (int i = 0; i < listCartDetails.size(); i++) {
//			total = total + listCartDetails.get(i).getPrice() * listCartDetails.get(i).getQuantity();
//		}
//		model.addAttribute("total", total);
//		return "client/cart/cart";        
		User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("total", totalPrice);

        return "client/cart/show";
    }
}
