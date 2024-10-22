package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	public List<Product> fetchAllProduct(){
		return productRepository.findAll();
	}
	
	public Product handleSaveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product fetchProductById(Long id) {
		return productRepository.findById(id).get();
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
