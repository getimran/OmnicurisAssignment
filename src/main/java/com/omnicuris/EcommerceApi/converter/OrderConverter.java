package com.omnicuris.EcommerceApi.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.omnicuris.EcommerceApi.dto.OrderDto;
import com.omnicuris.EcommerceApi.model.Order;

@Component
public class OrderConverter {
	
	@Autowired
	ModelMapper modelMapper;
	
	public OrderDto convertToDto(Order order) {		
		return modelMapper.map(order, OrderDto.class);
	}

}
