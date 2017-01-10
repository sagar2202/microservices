/**
 * 
 */
package demo.microservice.customer.repo;

import org.springframework.data.repository.CrudRepository;

import demo.microservice.customer.domain.Customer;

/**
 * @author Sagar Jain
 *
 */
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	
	public Customer findByCustomerid(int customerid);
}
