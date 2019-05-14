package com.omnicuris.EcommerceApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicuris.EcommerceApi.converter.ProductConverter;
import com.omnicuris.EcommerceApi.dto.ProductDto;
import com.omnicuris.EcommerceApi.model.Product;
import com.omnicuris.EcommerceApi.repository.ProductRepository;

/**
 * 
 */

/**
 * @author Imran
 *
 */

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;

	public Product addProduct(Product product) {		
		return productRepository.save(product);
	}
	
	public Iterable<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(Product product) {
		Optional<Product> opt = productRepository.findById(product.getId());
		if(opt.isPresent()) {
			Product p= opt.get();
			product.setCreatedTimestamp(p.getCreatedTimestamp());
		}
		return productRepository.save(product);
	}
	
	public boolean existById(Long id) {
		return productRepository.existsById(id);
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}
}
