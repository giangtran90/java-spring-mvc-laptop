package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartDetailRepository cartDetailRepository;

	public ProductService(ProductRepository productRepository
			, UserRepository userRepository
			, CartRepository cartRepository
			, CartDetailRepository cartDetailRepository) {
		super();
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
		this.cartDetailRepository = cartDetailRepository;
	}
	
	public List<Product> fetchAllProduct(){
		return this.productRepository.findAll();
	}
	
	public Product handleSaveProduct(Product product) {
		return this.productRepository.save(product);
	}

	public Product fetchProductById(Long id) {
		return this.productRepository.findById(id).get();
	}

	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	public void handleAddProductToCart(long id, String email, HttpSession session) {
		User user = this.userRepository.findByEmail(email);
		if (user != null) {
			// check xem user da co cart hay chua neu chua thi them moi vao
			Cart cart = cartRepository.findByUser(user);
			if (cart == null) {
				Cart nCart = new Cart();
				nCart.setUser(user);
				nCart.setSum(0);
				
				cart = this.cartRepository.save(nCart);
			}
			
			// Neu cart da ton tai thi save cart detail
			Optional<Product> productOptional = this.productRepository.findById(id);
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				
				// check CartDetail
				CartDetail oldCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
				if (oldCartDetail == null) {
					CartDetail cartDetail = new CartDetail();
					cartDetail.setCart(cart);
					cartDetail.setPrice(product.getPrice());
					cartDetail.setProduct(product);
					cartDetail.setQuantity(1);		
					this.cartDetailRepository.save(cartDetail);
					// luu sum sau khi them vao gio hang + them sum vao session de hien thi
					int s = cart.getSum() + 1;
					cart.setSum(s);
					this.cartRepository.save(cart);
					session.setAttribute("sum", s);
				} else {
					oldCartDetail.setQuantity(oldCartDetail.getQuantity() + 1);
					this.cartDetailRepository.save(oldCartDetail);
				}		
			}	
		}
	}
	
	public List<CartDetail> fetchCartDetailByUser(String email){
		User user = this.userRepository.findByEmail(email);
		if (user != null) {
			Cart cart = cartRepository.findByUser(user);
			if (cart != null) {
				return this.cartDetailRepository.findAllByCart(cart);
			}
		}
		return null;		
	}
}
