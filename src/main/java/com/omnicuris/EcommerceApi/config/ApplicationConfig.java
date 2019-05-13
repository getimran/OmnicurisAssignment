/**
 * 
 */
package com.omnicuris.EcommerceApi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Imran
 *
 */

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
	      ModelMapper modelMapper = new ModelMapper();
	      return modelMapper;
	}
	
}
