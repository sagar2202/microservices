package demo.microservice.payment.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Sagar Jain
 *
 */
@Entity
@Table(name= "payment")
public class Payment {
	@Id
	private int paymentid;
	private int customerid;	
	private String status;
	private double amount;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date paymentdate;
	
	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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

	
		
	public Payment (){
		
	}

	public Payment(int customerid, int paymentid, String status, double amount, Date paymentdate ) {
		this.customerid=customerid;
        this.paymentid=paymentid;
        this.status=status;
        this.amount=amount;
        this.paymentdate=paymentdate;
       
    }
	
}
