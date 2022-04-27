import java.util.*;

public class CustomerPage extends OwnerPage {
	//Creating a hashmap to store the food items and their quantity.
	HashMap<String, Integer> order;
	

	CustomerPage() {
		order = new HashMap<String, Integer>();
		
	}

	void displaymenu() {
		super.displaymenu();
	}
//Displays the customer's order
	void displayorder() {
		//Check if the  HashMap is empty.
		if (order.isEmpty()) {
			System.out.println("No items in order.");
			return;
		}
		//creating new hashset 
		Set<String> foodname = new HashSet<String>();
		//copy the foodnames from "order" hashmap by keyset() method.
		foodname = order.keySet();
		System.out.println("************************************************");
		System.out.println("FOOD \tQUANTITY \tPRICE \t\tTOTAL");
		System.out.println("************************************************");
		for (String food : foodname) {
			System.out.println(food + "\t" + order.get(food) + "\t\t" + getmenu().get(food) + "\t\t"
					+ getmenu().get(food) * order.get(food));
		}
		System.out.println("************************************************\n");
	}
	
	//Check if the menu contains the food item ordered by the customer
	//Then,adds the food item
	boolean orderfood(String food, int quantity) {
	
		if (!super.getmenu().containsKey(food))
			return false;
		order.put(food, quantity);
		return true;
	}
	
	//If the food item already exists in order, first remove the food item 
	//Then add the updated (food,quantity) in order.
	//If food item does not exist in order it will just add it as a new entry.
	boolean update(String food, int quantity) {
		 
		try {
			removefood(food);
			order.put(food, quantity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	//Check if the order contains the food item ordered by the customer
		//Then removes the food item
		boolean removefood(String food) {
			 
			if (!order.containsKey(food))
				return false;
			else
				order.remove(food);
			return true;
		}
	//calculate the total amount for the food items ordered including GSTTAX.
	double totalbill() {
	
		double total_amount = 0;
		//Initializing GSTTAX as 12 percentage.
		float GSTTax = 12F;
		//creating new hashset 
		Set<String> foodname = new HashSet<String>();
		foodname = order.keySet();
		//copy the names of the food from "order" hashmap by keyset() method.
		for (String food : foodname) {
			total_amount += (super.getmenu().get(food) * order.get(food));
		}
		double total_tax = GSTTax * total_amount / 100;
		return total_amount + total_tax;
	}
}