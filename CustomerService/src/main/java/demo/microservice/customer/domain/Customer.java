package demo.microservice.customer.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Sagar Jain
 *
 */
@Entity
@Table(name= "customer")
public class Customer {
	@Id
	private int customerid;
	private String firstname;
	private String lastname;
	private String address;
	
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
	
	
	public Customer(){
		
	}
	
	public Customer(int customerid, String fName, String lName, String address) {
		this.customerid=customerid;
        this.firstname=fName;
        this.lastname=lName;
        this.address=address;
       
    }


}
