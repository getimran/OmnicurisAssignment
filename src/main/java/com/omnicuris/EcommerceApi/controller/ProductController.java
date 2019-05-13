/**
 * 
 */
package com.omnicuris.EcommerceApi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.EcommerceApi.converter.ProductConverter;
import com.omnicuris.EcommerceApi.dto.ProductDto;
import com.omnicuris.EcommerceApi.model.Product;
import com.omnicuris.EcommerceApi.service.ProductService;

/**
 * @author Imran
 *
 */

@RestController
@RequestMapping("/omnicuris/v1/ecommerce/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductConverter productConverter;

	@GetMapping("/test")
	public String test() {
		return "Application is working fine...";
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> getAllProd(){
		
		Iterable<Product> products= productService.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(IterableUtils.toList(products).stream().map(product -> productConverter.convertToDto(product)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ProductDto> addProduct(@RequestBody(required=true) Product product){
		return new ResponseEntity<ProductDto>(productConverter.convertToDto(productService.addProduct(product)), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(required=true) Long id, @RequestBody(required=true) Product product){
		if(!productService.existById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id "+id+" not found.");
		}
		product.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(productConverter.convertToDto(productService.updateProduct(product)));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(required=true) Long id){
		if(!productService.existById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id "+id+" not found.");
		}
		productService.deleteById(id);
		if(productService.existById(id)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deletion unsuccessfull."); 
		}
		return ResponseEntity.status(HttpStatus.OK).body("Product with id "+id+" deleted successfully.");
	}
}
