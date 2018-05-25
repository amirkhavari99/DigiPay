public class Good {
	private String name;
	private int price;

	// the good constructor, and also a good constructor :D
	public Good(String name, int price){
		this.name = name;
		this.price = price;
	}

	public int getPrice(){
		return this.price;
	}

	public String getName(){
		return this.name;
	}

	@Override
	public String toString(){
		return String.format(name + " :\t\t" + price + "$");
	}
}