/**
 * 
 */
package demo.microservice.payment.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 * @author Sagar Jain
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PaymentFailureException(int customerId) {
		super("Failed to Process Payment for: " + customerId);
	}
}