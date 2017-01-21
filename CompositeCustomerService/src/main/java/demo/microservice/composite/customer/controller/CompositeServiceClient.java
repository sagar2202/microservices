package demo.microservice.composite.customer.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import demo.microservice.account.domain.Account;
import demo.microservice.composite.customer.domain.Aggregated;
import demo.microservice.customer.domain.Customer;
import demo.microservice.payment.domain.Payment;

/**
 * @author Sagar Jain
 *
 */

@RestController
public class CompositeServiceClient {
	private static final Logger log = LogManager.getLogger(CompositeServiceClient.class);
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * @param customerid
	 * @return
	 */

	@HystrixCommand(fallbackMethod = "handleCustFailure")
	public ResponseEntity<Customer> getCustomerDetails(int customerid) {

		ResponseEntity<Customer> customerResult = null;
		Customer cust = null;

		try {
			ResponseEntity<String> custResultStr = restTemplate
					.getForEntity("http://localhost:7002/customer/" + customerid, String.class);
			if (custResultStr.getBody() == null) {
				customerResult = createNotFoundResponse(cust);
			} else {
				cust = convertToCustomerObj(custResultStr);
				customerResult = createOkResponse(cust);

			}
		} catch (Throwable t) {
			log.error("Customer Service Failed: " + t.getMessage());
			throw t;
		}

		return customerResult;
	}

	/**
	 * Fallback method for getCustomerDetails()
	 *
	 * @param customerid
	 * @return
	 */
	public ResponseEntity<Customer> handleCustFailure(int customerid) {
		log.warn("Executing Fallback method for customer-service failure");
		return createResponse(null, HttpStatus.BAD_GATEWAY);
	}

	/**
	 * @param customerid
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "handleAccFailure")
	public ResponseEntity<Account> getAccountDetails(int customerid) {
		ResponseEntity<Account> accountResult = null;
		Account acc = null;

		try {
			ResponseEntity<String> accountResultStr = restTemplate
					.getForEntity("http://localhost:7003/account/" + customerid, String.class);
			
			if (accountResultStr.getBody() == null) {
				accountResult = createNotFoundResponse(acc);
			} else {
				acc = convertToAccountObj(accountResultStr);
				accountResult = createOkResponse(acc);
			}

		} catch (Throwable t) {
			log.error("Account Service Failed: " + t.getMessage());
			throw t;
		}

		return accountResult;

	}
	
	/**
	 * Fallback method for getAccountDetails()
	 *
	 * @param customerid
	 * @return
	 */
	public ResponseEntity<Account> handleAccFailure(int customerid) {
		log.warn("Executing Fallback method for account-service failure");
		return createResponse(null, HttpStatus.BAD_GATEWAY);
	}


	/**
	 * @param customerid
	 * @param balance
	 * @return
	 */
	public ResponseEntity<Payment> getPaymentDetails(int customerid, double balance) {
		ResponseEntity<Payment> paymentResult = null;
		Payment payment = null;
		try {
			ResponseEntity<String> paymentResultStr = restTemplate.getForEntity(
					"http://localhost:7004/payment?customerid=" + customerid + "&amount=" + balance, String.class);
			payment = convertToPaymentObj(paymentResultStr);

			paymentResult = createOkResponse(payment);
			/*
			 * if (!paymentResult.getStatusCode().is2xxSuccessful()) {
			 * 
			 * //return createResponse(null, paymentResult.getStatusCode()); }
			 */

		} catch (final HttpClientErrorException e) {
			log.error("Payment Service Status Code: " + e.getStatusCode());
			paymentResult = createISERequestResponse(payment);
		} catch (final HttpServerErrorException e) {
			log.error("Payment Service Status Code: " + e.getStatusCode());
			paymentResult = createISERequestResponse(payment);
		}
		return paymentResult;
	}

	public Customer convertToCustomerObj(ResponseEntity<String> response) {
		try {
			return getCustomerReader().readValue(response.getBody());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Account convertToAccountObj(ResponseEntity<String> response) {
		try {
			return getAccountReader().readValue(response.getBody());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Payment convertToPaymentObj(ResponseEntity<String> response) {
		try {
			return getPaymentReader().readValue(response.getBody());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ObjectReader customerRdr = null;

	private ObjectReader getCustomerReader() {

		if (customerRdr != null)
			return customerRdr;

		ObjectMapper mapper = new ObjectMapper();
		return customerRdr = mapper.reader(Customer.class);
	}

	private ObjectReader accountRdr = null;

	private ObjectReader getAccountReader() {

		if (accountRdr != null)
			return accountRdr;

		ObjectMapper mapper = new ObjectMapper();
		return accountRdr = mapper.reader(Account.class);
	}

	private ObjectReader paymentRdr = null;

	private ObjectReader getPaymentReader() {

		if (paymentRdr != null)
			return paymentRdr;

		ObjectMapper mapper = new ObjectMapper();
		return paymentRdr = mapper.reader(Payment.class);
	}

	public <T> ResponseEntity<T> createNotFoundResponse(T body) {
		return createResponse(body, HttpStatus.NOT_FOUND);
	}

	public <T> ResponseEntity<T> createISERequestResponse(T body) {
		return createResponse(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public <T> ResponseEntity<T> createOkResponse(T body) {
		return createResponse(body, HttpStatus.OK);
	}

	public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
		return new ResponseEntity<>(body, httpStatus);
	}

}