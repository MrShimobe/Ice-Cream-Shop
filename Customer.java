import java.io.IOException;

public class Customer {
	private String firstName;
	private String lastName;
	private static int customerID = 0;;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		customerID++;
	}

	public Customer() throws IOException {

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
