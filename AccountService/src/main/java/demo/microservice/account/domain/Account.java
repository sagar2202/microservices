package demo.microservice.account.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sagar Jain
 *
 */
@Entity
@Table(name= "account")
public class Account {
	@Id
	private int customerid;
	private double datalimit;
	private double availabledata;
	private double balance;
	private Date duedate;
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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
	
	public Account (){
		
	}

	public Account(int customerid, double datalimit, double availabledata, double balance, Date duedate ) {
		this.customerid=customerid;
        this.datalimit=datalimit;
        this.availabledata=availabledata;
        this.balance=balance;
        this.duedate=duedate;
       
    }
	
}
