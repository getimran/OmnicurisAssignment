/**
 * 
 */
package com.omnicuris.EcommerceApi.enums;

/**
 * @author Imran
 *
 */
public enum OrderStatus {

	success("SUCCESS"),failed("FAILED");
	
	private final String status;

	private OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}
