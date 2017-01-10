/**
 * 
 */
package demo.microservice.account.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 * @author Sagar Jain
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountDetailsNotFoundException(int customerId) {
		super("No such account for: " + customerId);
	}
}