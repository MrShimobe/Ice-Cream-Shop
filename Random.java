
public class Random {
	public static void main(String[] args) {

		Customer cust = new Customer("Bob", "Sanchez");
		
		Cone cone1 = new Cone("Waffle", true, "Chocolate", 1);
		Cone cone2 = new Cone("Sugar", false, "Vanilla", 2);
		Cone cone3 = new Cone("Light", false, "Cherry", 3);
		Cone cone4 = new Cone("Plastic", true, "Strawberry", 4);
		
		Cone[] array = new Cone[4];
		array[0] = cone1;
		array[1] = cone2;
		array[2] = cone3;
		array[3] = cone4;
		
		Order order = new Order(cust, array);
		
		System.out.println(order);
		
		
}
}
