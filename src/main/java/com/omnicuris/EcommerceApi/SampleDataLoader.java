package com.omnicuris.EcommerceApi;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.omnicuris.EcommerceApi.model.Product;
import com.omnicuris.EcommerceApi.service.ProductService;

@Component
public class SampleDataLoader implements ApplicationRunner {

	@Autowired
	ProductService productService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		productService.addProduct(new Product("Green MedPack", "This is Green Medi pack.", new BigDecimal(105),23));
		productService.addProduct(new Product("Ayurveda Cream 200ml", "This is Ayurveda Cream 200ml", new BigDecimal(235.89),13));
	}

	
}
