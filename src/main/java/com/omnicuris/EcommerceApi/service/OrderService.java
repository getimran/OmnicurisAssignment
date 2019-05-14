/**
 * 
 */
package com.omnicuris.EcommerceApi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicuris.EcommerceApi.converter.OrderConverter;
import com.omnicuris.EcommerceApi.dto.OrderDto;
import com.omnicuris.EcommerceApi.dto.OrderProductData;
import com.omnicuris.EcommerceApi.enums.OrderStatus;
import com.omnicuris.EcommerceApi.model.Order;
import com.omnicuris.EcommerceApi.model.Product;
import com.omnicuris.EcommerceApi.repository.OrderRepository;
import com.omnicuris.EcommerceApi.repository.ProductRepository;

/**
 * @author Imran
 *
 */
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderConverter orderConverter;
	
	public OrderDto placeOrder(List<OrderProductData> orderProductsData, String userEmail) {
		
		OrderDto orderDto= new OrderDto();
		Order order=new Order();
		int totalItems=0;
		BigDecimal totalAmount=new BigDecimal(0);
		for(OrderProductData orderProductData: orderProductsData) {
			
			Product product= orderProductData.getProduct();
			int orderedQuantity = orderProductData.getQuantity();
			
			if(!productRepository.existsById(product.getId())) {
				orderDto.setId(0L);
				orderDto.setOrderStatus(OrderStatus.failed.getStatus());
				orderDto.setMessage("Order Failed. Product with id "+product.getId()+" doesn't exist.");
				orderDto.setOrderTotal(new BigDecimal(0));
				orderDto.setTotalItems(0);
				return orderDto;
			}

			Product prod= productRepository.findById(product.getId()).get();
			if(prod.getStockQuantity()<orderedQuantity) {
				orderDto.setId(0L);
				orderDto.setOrderStatus(OrderStatus.failed.getStatus());
				String msg="";
				if(prod.getStockQuantity()==0) {
					msg= "Order Failed. Product with id "+product.getId()+" is Out of Stock.";
				}else {
					msg= "Order Failed. Product with id "+product.getId()+" is in low stock. InStock: "+prod.getStockQuantity()+" OrderedQuantity: "+orderedQuantity;
				}
				orderDto.setMessage(msg);
				orderDto.setOrderTotal(new BigDecimal(0));
				orderDto.setTotalItems(0);
				return orderDto;
			}
			totalItems+= orderedQuantity;
			totalAmount = totalAmount.add(prod.getPrice()==null?new BigDecimal(0):(prod.getPrice().multiply(new BigDecimal(orderedQuantity))));			
		}
		
		order.setOrderTotal(totalAmount);
		order.setTotalItems(totalItems);
		order.setUserEmail(userEmail);
		order.setOrderStatus(OrderStatus.success.getStatus());
		order.setMessage("Order Placed Successfully !!");
		
		Order placed =orderRepository.save(order);
		orderDto =  orderConverter.convertToDto(placed);
/*		orderDto.setOrderStatus(OrderStatus.success.getStatus());
		orderDto.setMessage("Order Placed Successfully !!");*/
		return orderDto;
	}
	
	
	public Iterable<Order> findAllOrders(){		
		return orderRepository.findAll();		
	}
	
}
