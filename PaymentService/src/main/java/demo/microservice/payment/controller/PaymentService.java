package demo.microservice.payment.controller;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import demo.microservice.payment.domain.Payment;
import demo.microservice.payment.exception.PaymentFailureException;
import demo.microservice.payment.exception.PaymentNotFoundException;
import demo.microservice.payment.repo.PaymentRepo;

/**
 * @author Sagar Jain
 *
 */
@RestController
public class PaymentService {
	private static final Logger log = LogManager.getLogger(PaymentService.class);
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	Random rng = new Random();
	
	@Autowired
	private PaymentRepo paymentRepo;

	@RequestMapping("/payment")
	@ResponseBody
	public Payment postPayment(@RequestParam("customerid") int customerid, @RequestParam("amount") float amount) throws JsonProcessingException {


		try {
			Payment payment = processPayment(customerid, amount);
			paymentRepo.save(payment);
			log.info("Payment Details:" + ow.writeValueAsString(payment));
			return payment;
		} catch (Exception e) {
			log.error("Payment Failed for :" + customerid);
			throw new PaymentFailureException(customerid);
		}

	}

	@RequestMapping("/payment/status")
	@ResponseBody
	public String getStatus(@RequestParam("paymentid") int paymentid) throws JsonProcessingException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		Payment payment = paymentRepo.findOne(paymentid);
		if (payment == null) {
			log.error("Payment not found for :" + paymentid);
			throw new PaymentNotFoundException(paymentid);
		} else {
			log.info("Payment Details:" + ow.writeValueAsString(payment));
			return payment.getStatus();
		}

	}

	/**
	 * @param customerid
	 * @throws Exception 
	 * 
	 */
	private Payment processPayment(int customerid, float amount) throws Exception {
		/*
		 * TBD Logic for some external service which process credit card payment. For now we
		 * will assume payment is processed. Lets update the status in Payment
		 * DB by generating the payment id
		 * 
		 */

		int paymentid =  Math.abs((int) (rng.nextInt() % 100000000000000L)) ;
		if(paymentid % 2 == 0)
	      return new Payment(customerid, paymentid, "SUCCESS", amount, new Date());
		else 
		  throw  new Exception();
	}

}
