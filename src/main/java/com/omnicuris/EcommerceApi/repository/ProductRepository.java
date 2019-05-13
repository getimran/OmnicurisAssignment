/**
 * 
 */
package com.omnicuris.EcommerceApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.omnicuris.EcommerceApi.model.Product;

/**
 * @author Imran
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long>{

}
