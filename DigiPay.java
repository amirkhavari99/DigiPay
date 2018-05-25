import java.util.Scanner;

public class DigiPay {

	/*
	* this function sends a string to console
	*/
	private static void output(String outString){
		System.out.println(outString);
	}

	/*
	*	This Function reads goods' list, then inserts them into a list.
	*	TODO: Connect to the database and read goods' list from there.
	*/
	private static Good[] initializeGoods(){
		Good[] theGoods = new Good[3];
		theGoods[0] = new Good("Monitor", 20);
		theGoods[1] = new Good("Television", 50);
		theGoods[2] = new Good("Laptop", 500);
		return theGoods;
	}

	/*
	*	This function writes user choices into output,
	*	it contains goods' list and submit.
	*/
	private static void writeGoodChoices(Good[] goods){
		for (int i=0; i<3; i++) {
			output((i+1) + ". " + goods[i].toString());
		}
		output("0. Submit");
	}

	/*
	*	This functions clears the previous outputs printed on the screen,
	*	so we can have a freshen and updated data provided to the user.
	*/
	private static void clearOutput(){
		// Send the clear command to console!
		output("\033[H\033[2J");
	}

	/*
	*	This function gets a number from user.
	*/
	private static int getUserInputAsInt(){
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		return i;
	}

	/*
	*	This function gets a string from user.
	*/
	private static String getUserInputAsString(){
		Scanner scan = new Scanner(System.in);
		String i = scan.nextLine();
		return i;
	}

	/*
	*	This function shows user's currecnt order, including its total price.
	*/
	private static void showCurrentOrder(Order theOrder){
		output(theOrder.toString());
	}

	/*
	*	This function refreshes the data we are showing the user,
	*	first it clears all of the output and then it writes them
	*	all again with updated values so user can understand where he is.
	*/
	private static void updateCurrentState(String userName, Order theOrder){
		clearOutput();
		output("Hi " + userName + "! Welcome to our shop:\n");
		//output("Your Current Order is as following:");
		showCurrentOrder(theOrder);
		output("\n\n");
	}

	/*
	*	This function shows the user the goods we provide,
	*	then it writes out user good choices.
	*/
	private static void showGoods(String userName, Good[] goods, Order theOrder){
		updateCurrentState(userName, theOrder);
		output("* If you want to add some goods to your order, provide its number.");
		output("* If it seems good you can submit your order by entering the number 0.");
		writeGoodChoices(goods);
	}

	/*
	*	This function show the user that the good is selected,
	*	and then asks him how many of that good he wants.
	*/
	private static void askNumberOfGood(String userName, Good good, Order theOrder){
		updateCurrentState(userName, theOrder);
		String goodName = good.getName();
		int goodPrice = good.getPrice();
		String orderName = theOrder.getOrderName();
		output("You have selected " + goodName + " with the price fee of " + goodPrice + "$,");
		output("How many " + goodName + "s you want to add to '" + orderName + "'?");
		output("* Enter the number of goods you want to add");
		output("0. Or just enter 0 to cancel adding this good.");
	}

	/*
	*	This function applies user's good select on the current order
	*	and shows it to the user.
	*/
	private static void applyGoodAdd(String userName, Good[] goods, Order theOrder, int goodIndex, int number){
		updateCurrentState(userName, theOrder);
		if (number == 1){
			output("A " + goods[goodIndex].getName() + " has been added to your order.");
		}else{
			output(number + " " + goods[goodIndex].getName() + "s has been added to your order.");
		}
		output("\n\n");
		output("* If you want to add some goods to your order, provide its number.");
		output("* If it seems good you can submit your order by entering the number 0.");
		writeGoodChoices(goods);
	}

	/*
	*	This function submits user's order.
	*	TODO: Connect to the database and Save User's Order.
	*/
	private static void submitOrder(String userName, Order theOrder){
		updateCurrentState(userName, theOrder);
		output("Your order has been submitted, you don't need to pay for it or");
		output("enter your address, your goods will be sent to you right now. :D");
		output("Thanks for your purchase, Press enter to exit...");
	}	

	public static void main(String[] args) {

		//	Get goods' list
		Good[] goods = initializeGoods();

		output("Hi, What's your name?");

		String userName = getUserInputAsString();

		output("Please provide a name for your order:");

		String orderName = getUserInputAsString();
		
		Order theOrder = new Order(userName, orderName);

		showGoods(userName, goods, theOrder);

		int selectedGoodIndex = getUserInputAsInt();

		while (selectedGoodIndex != 0){ // check if user has selected a good
			
			askNumberOfGood(userName, goods[selectedGoodIndex-1], theOrder);

			int number = getUserInputAsInt();

			if (number != 0){
				theOrder.addToOrder(goods[selectedGoodIndex-1], number);
				applyGoodAdd(userName, goods, theOrder, selectedGoodIndex-1, number);
			}else{
				showGoods(userName, goods, theOrder);
			}

			selectedGoodIndex = getUserInputAsInt();
		}
		//	User has submitted his order, so we can show him the order details and summary.
		submitOrder(userName, theOrder);

		getUserInputAsString();
	}
}
