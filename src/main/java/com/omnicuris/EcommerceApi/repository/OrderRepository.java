/**
 * 
 */
package com.omnicuris.EcommerceApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.omnicuris.EcommerceApi.model.Order;

/**
 * @author Imran
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

}
