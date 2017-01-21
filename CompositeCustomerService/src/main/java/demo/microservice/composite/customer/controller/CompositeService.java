package demo.microservice.composite.customer.controller;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import demo.microservice.account.domain.Account;
import demo.microservice.composite.customer.domain.Aggregated;
import demo.microservice.customer.domain.Customer;
import demo.microservice.payment.domain.Payment;

/**
 * @author Sagar Jain
 *
 */

@RestController
public class CompositeService {
	@Autowired
	CompositeServiceClient srvClient;
	private static final Logger log = LogManager.getLogger(CompositeService.class);
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

	@RequestMapping("/customer/{customerid}")
	public ResponseEntity<Aggregated> getCustomer(@PathVariable int customerid) throws JsonProcessingException {

		ResponseEntity<Customer> custDtl = null;
		ResponseEntity<Account> accDtl = null;
		ResponseEntity<Payment> paymentDtl = null;
		ResponseEntity<Aggregated> aggregatedResult;

		custDtl = srvClient.getCustomerDetails(customerid);

		// Call Account service only if We have Customer validation done.
		if (custDtl != null && !custDtl.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			accDtl = srvClient.getAccountDetails(customerid);
		}

		// If Account information is available and due date is today then only
		// call payment
		if (accDtl != null && accDtl.getBody() != null && (DateUtils.isSameDay(new Date(), accDtl.getBody().getDuedate()))) {
			paymentDtl = srvClient.getPaymentDetails(customerid, accDtl.getBody().getBalance());

		}

		aggregatedResult = srvClient.createOkResponse(new Aggregated(custDtl, accDtl, paymentDtl));

		log.info("Agreegated Customer Details:" + ow.writeValueAsString(aggregatedResult));
		return aggregatedResult;
	}

}