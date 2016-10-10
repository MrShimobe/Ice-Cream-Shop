
public class Customer {
	private String firstName;
	private String lastName;
	private String customerID;

	public Customer(String firstName, String lastName, String customerID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerID = customerID;
	}

	public Customer(String customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCustomerID() {
		return customerID;
	}

}
