package com.omnicuris.EcommerceApi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.EcommerceApi.converter.OrderConverter;
import com.omnicuris.EcommerceApi.dto.OrderDto;
import com.omnicuris.EcommerceApi.dto.OrderProductData;
import com.omnicuris.EcommerceApi.dto.ProductDto;
import com.omnicuris.EcommerceApi.enums.OrderStatus;
import com.omnicuris.EcommerceApi.model.Order;
import com.omnicuris.EcommerceApi.model.Product;
import com.omnicuris.EcommerceApi.service.OrderService;

/**
 * @author Imran
 *
 */

@RestController
@RequestMapping("/omnicuris/v1/ecommerce/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderConverter orderConverter;
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderDto>> findAllOrder(){
		Iterable<Order> allOrders= orderService.findAllOrders();
		return new ResponseEntity<List<OrderDto>>(IterableUtils.toList(allOrders).stream().map(order -> orderConverter.convertToDto(order)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PostMapping("/place/{userEmail}")
	public ResponseEntity<OrderDto> placeOrder(@PathVariable String userEmail,@RequestBody List<OrderProductData> orderProductsData){
		
		OrderDto orderDto= orderService.placeOrder(orderProductsData, userEmail);
		if(orderDto.getOrderStatus().equals(OrderStatus.failed.getStatus())) {
			return ResponseEntity.badRequest().body(orderDto);
		}		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
	}
}
