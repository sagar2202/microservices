/**
 * 
 */
package demo.microservice.payment.repo;

import org.springframework.data.repository.CrudRepository;
import demo.microservice.payment.domain.Payment;

/**
 * @author Sagar Jain
 *
 */
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
	
	public Payment findByCustomerid(int customerid);
}
