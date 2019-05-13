package com.omnicuris.EcommerceApi.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnicuris.EcommerceApi.dto.ProductDto;
import com.omnicuris.EcommerceApi.model.Product;

@Component
public class ProductConverter {
	
	@Autowired
	ModelMapper modelMapper;
	
	public ProductDto convertToDto(Product product) {		
		return modelMapper.map(product, ProductDto.class);
	}
	
	public Product convertToModel(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}
}

