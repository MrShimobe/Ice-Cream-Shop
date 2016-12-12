public class Cone implements Cloneable {
	private String coneType;
	private boolean iceCreamOrYogurt;
	private String flavor;
	private String numberOfScoops;
	private int cupSize;

	final static double costPerScoop = .50;
	final static double costPerIceCreamCone = .75;

	public Cone(String coneType, boolean iceCreamOrYogurt, String flavor, String numberOfScoops) {
		this.coneType = coneType;
		this.iceCreamOrYogurt = iceCreamOrYogurt;
		this.flavor = flavor;
		this.numberOfScoops = numberOfScoops;
	}

	public Cone(String coneType, boolean iceCreamOrYogurt, String flavor) {
		this.coneType = coneType;
		this.iceCreamOrYogurt = iceCreamOrYogurt;
		this.flavor = flavor;
	}

	public Cone(String coneType, boolean iceCreamOrYogurt, String flavor, String numberOfScoops, int cupSize) {
		this.coneType = coneType;
		this.iceCreamOrYogurt = iceCreamOrYogurt;
		this.flavor = flavor;
		this.numberOfScoops = numberOfScoops;
		this.cupSize = cupSize;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getConeType() {
		return coneType;
	}

	public void setConeType(String coneType) {
		this.coneType = coneType;
	}

	public boolean isIceCreamOrYogurt() {
		return iceCreamOrYogurt;
	}

	public int isIceCreamOrYogurt2() {
		if (iceCreamOrYogurt == true)
			return 1;
		return 0;

	}

	public void setIceCreamOrYogurt(boolean iceCreamOrYogurt) {
		this.iceCreamOrYogurt = iceCreamOrYogurt;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getNumberOfScoops() {
		return numberOfScoops;
	}

	public void setNumberOfScoops(String numberOfScoops) {
		this.numberOfScoops = numberOfScoops;
	}

	public int getCupSize() {
		return cupSize;
	}

	public void setCupSize(int cupSize) {
		this.cupSize = cupSize;
	}

	public double getCost() {
		return (costPerScoop * Integer.parseInt(getNumberOfScoops()) + costPerIceCreamCone);
	}

	@Override
	public String toString() {
		return flavor + " " + (iceCreamOrYogurt ? "IceCream" : "Yogurt") + " " + coneType + " with " + numberOfScoops
				+ " scoops";
	}

}
