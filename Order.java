public class Order {
	private String ordername;
	private String userName;
	private String goodsList;
	private double totalPrice;

	// the order constructor
	public Order(String userName, String ordername){
		this.ordername = ordername;
		this.userName = userName;
		this.goodsList = "";
		this.totalPrice = 0;
	}

	/*
	*	This function returns a double number that is greater than number 1.
	*	We use this number is Tax Multiplier which can add the tax value
	*	to the total price of the order.
	*/
	private double getTaxMultiplier(){
		// TODO: get tax value from database
		double taxValue = 0.10;
		if(taxValue == 0){
			return 1.0;
		}else{
			return 1.0 + taxValue;
		}
	}

	public void addToOrder(Good theGood, int number){
		int untaxedPrice = number * (theGood.getPrice());
		this.totalPrice += getTaxMultiplier() * untaxedPrice;
		if (number == 1){
			this.goodsList += ("* A " + theGood.getName() + "\n");
		}
		else{
			this.goodsList += ("* " + number + " " + theGood.getName() + "s\n");
		}
	}

	public String getOrderName(){
		return this.ordername;
	}

	public String getUserName(){
		return this.userName;
	}

	public String getGoodsList(){
		if(this.goodsList == ""){
			return "\tNothing has added to the order yet.\n";
		}else{
			return this.goodsList;
		}
	}

	public double getTotalPrice(){
		return this.totalPrice;
	}

	@Override
	public String toString(){
		return String.format("User name: \t\t" + this.getUserName() + "\nOrder name: \t\t" + this.getOrderName() + 
							 "\nOrder details in order you added them:\n" + this.getGoodsList() + 
							 "Total Price: \t\t" + this.getTotalPrice() + "$" + "\t(which is multiplied by " + getTaxMultiplier() + " For Taxes).");
	}
}