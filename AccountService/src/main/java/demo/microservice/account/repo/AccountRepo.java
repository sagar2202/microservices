/**
 * 
 */
package demo.microservice.account.repo;

import org.springframework.data.repository.CrudRepository;

import demo.microservice.account.domain.Account;

/**
 * @author Sagar Jain
 *
 */
public interface AccountRepo extends CrudRepository<Account, Integer> {
	
	public Account findByCustomerid(int customerid);
}
