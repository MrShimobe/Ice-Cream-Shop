public class Order {

	protected Customer customer;
	protected Cone[] cone;
	
	public Order(Customer customer, Cone[] array) {
		this.customer = customer;
		cone = array;
	}

	public Customer getCust() {
		return customer;
	}

	public void setCust(Customer customer) {
		this.customer = customer;
	}

	public double getTotal() {
		double total = 0;
		for(int i = 0; i < cone.length; i++)
			total+= cone[i].getCost();
		return total;
	}

	@Override
	public String toString() {
		StringBuilder printOut = new StringBuilder(20);
		printOut.append(customer.toString() + "\n");
		for(int i = 0; i < cone.length; i++)
			printOut.append(cone[i].toString() + "\n");
		
		return printOut.toString();
	}
	
}
