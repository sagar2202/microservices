/**
 * 
 */
package demo.microservice.composite.customer.domain;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import demo.microservice.account.domain.Account;
import demo.microservice.customer.domain.Customer;
import demo.microservice.payment.domain.Payment;

/**
 * @author Sagar Jain
 *
 */
public class Aggregated {
	private int customerServiceStatus;
	private int customerid;
	private String firstname;
	private String lastname;
	private String address;
	private int acccountServiceStatus;
	private double datalimit;
	private double availabledata;
	private double balance;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date duedate;
	private int paymentServiceStatus;
	private int paymentid;
	private String status;
	private double amount;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date paymentdate;

	
	

	public int getCustomerServiceStatus() {
		return customerServiceStatus;
	}

	public void setCustomerServiceStatus(int customerServiceStatus) {
		this.customerServiceStatus = customerServiceStatus;
	}

	public int getAcccountServiceStatus() {
		return acccountServiceStatus;
	}

	public void setAcccountServiceStatus(int acccountServiceStatus) {
		this.acccountServiceStatus = acccountServiceStatus;
	}

	public int getPaymentServiceStatus() {
		return paymentServiceStatus;
	}

	public void setPaymentServiceStatus(int paymentServiceStatus) {
		this.paymentServiceStatus = paymentServiceStatus;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDatalimit() {
		return datalimit;
	}

	public void setDatalimit(double datalimit) {
		this.datalimit = datalimit;
	}

	public double getAvailabledata() {
		return availabledata;
	}

	public void setAvailabledata(double availabledata) {
		this.availabledata = availabledata;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Aggregated(ResponseEntity<Customer> customerResult, ResponseEntity<Account> accountResult,
			ResponseEntity<Payment> paymentResult) {

		if (customerResult.getStatusCodeValue() == HttpStatus.NOT_FOUND.value() ) {
			this.customerServiceStatus = HttpStatus.NOT_FOUND.value();
		} else {
			this.customerid = customerResult.getBody().getCustomerId();
			this.firstname = customerResult.getBody().getfirstname();
			this.lastname = customerResult.getBody().getlastname();
			this.address = customerResult.getBody().getAddress();
			this.customerServiceStatus=HttpStatus.OK.value();
		}

		
		if(accountResult !=null ) {
			this.datalimit = accountResult.getBody().getDatalimit();
			this.availabledata = accountResult.getBody().getAvailabledata();
			this.balance = accountResult.getBody().getBalance();
			this.duedate = accountResult.getBody().getDuedate();
			this.acccountServiceStatus=HttpStatus.OK.value();
		}

		
		if (paymentResult!= null && paymentResult.getStatusCodeValue() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			this.paymentServiceStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
		} else if(paymentResult != null){
			this.paymentid = paymentResult.getBody().getPaymentid();
			this.status = paymentResult.getBody().getStatus();
			this.amount = paymentResult.getBody().getAmount();
			this.paymentdate = (Date) paymentResult.getBody().getPaymentdate();
			this.paymentServiceStatus = HttpStatus.OK.value();			
		}
	}

	public Aggregated(ResponseEntity<Customer> customerResult, ResponseEntity<Account> accountResult) {
		this.customerid = customerResult.getBody().getCustomerId();
		this.firstname = customerResult.getBody().getfirstname();
		this.lastname = customerResult.getBody().getlastname();
		this.address = customerResult.getBody().getAddress();

		this.datalimit = accountResult.getBody().getDatalimit();
		this.availabledata = accountResult.getBody().getAvailabledata();
		this.balance = accountResult.getBody().getBalance();
		this.duedate = accountResult.getBody().getDuedate();

	}

	public Aggregated(ResponseEntity<Customer> customerResult) {
		this.customerid = customerResult.getBody().getCustomerId();
		this.firstname = customerResult.getBody().getfirstname();
		this.lastname = customerResult.getBody().getlastname();
		this.address = customerResult.getBody().getAddress();
	}

}
