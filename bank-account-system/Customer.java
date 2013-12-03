package bank;
/**
 * This class sets the guidelines for the customer object.
 * 
 * Jacob Coddaire
 * CIS 163
 */
import java.io.Serializable;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String ssn;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	/*********************************************************************************************
    This constructor creates the basic functionality for the Customer Object
    *********************************************************************************************/
	public Customer(String name, String ssn, String address, String  city, String state, String zip, String phone)
	{
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public String getSsn() {
		return ssn;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getPhone() {
		return phone;
	}
	public String toString()
	{
		return name;
	}
}