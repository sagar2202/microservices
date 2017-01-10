package demo.microservice.customer.domain;
/**
 * @author Sagar Jain
 *
 */
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "customer")
public class Customer {
	@Id
	private int customerid;
	private String firstname;
	private String lastname;
	private String address;
	private Date duedate;
	
	public int getCustomerId() {
		return customerid;
	}
	public void setCustomerId(int customerid) {
		this.customerid = customerid;
	}
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getlastname() {
		return lastname;
	}
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getduedate() {
		return duedate;
	}
	public void setduedate(Date duedate) {
		this.duedate = duedate;
	}
	
	public Customer(){
		
	}
	
	public Customer(int custoemerid, String fName, String lName, String address, Date date) {
		this.customerid=custoemerid;
        this.firstname=fName;
        this.lastname=lName;
        this.address=address;
        this.duedate=date;
    }


}
