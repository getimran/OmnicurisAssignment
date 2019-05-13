/**
 * 
 */
package com.omnicuris.EcommerceApi.dto;

import com.omnicuris.EcommerceApi.model.Product;

/**
 * @author Imran
 *
 */
public class OrderProductData {

	private Product product;
	
	private int quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
