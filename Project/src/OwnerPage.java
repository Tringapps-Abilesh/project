import java.util.*;
//static HashMap to store all the food items and their respective price in menu. 
//Declared static so as to share same menu for all customers.
public class OwnerPage {
	
	static HashMap<String, Float> menu;
//Creating a hashmap to store the menu items with food items and their price values.
	OwnerPage(){
		menu=new HashMap<String, Float>();
	}
	
//Checks the username and password.
	boolean authorization(String username, String password) {
		return username.equals("Abilesh") && password.equals("Abi@123");
	}
	
// If menu already contains the food item don't add it in menu again and return false.
//Otherwise add the food item in menu and return true.
	boolean additem(String food, float price) {
		if (getmenu().containsKey(food))
			return false;
		getmenu().put(food, price);
		return true;
	}
// First check if food the item exists in menu. If exists then delete.
// Otherwise  return false
	boolean deleteitem(String food) {
		 // First check if food item exists in menu. If exists then delete and return
		 // true, else return false
		if (getmenu().containsKey(food)) {
			getmenu().remove(food);
			return true;
		}
		return false;
	}
	//First delete the existing food. 
	//If no such food is found, it adds food to the menu.
	//Otherwise updates the price
	boolean update(String food, float price) {
		try {
			deleteitem(food);
			additem(food, price);
		} catch (Exception e) {
			return false;
		}
		return true;

	}
//Displaying the food items in the menu with their prices.
	void displaymenu() {
		if (getmenu().isEmpty()) {
			System.out.println("******Menu is empty.*******1");
			return;
		}
		Set<String> foodNames = new HashSet<String>();
		foodNames = getmenu().keySet();
		System.out.println("***************************************");
		System.out.println("FOOD \t PRICE");
		System.out.println("***************************************");
		for (String food : foodNames) {
			System.out.println(food + "\t" + getmenu().get(food));
		}
		System.out.println("***************************************");
	}
//Returns the menu.
	public static HashMap<String, Float> getmenu() {
		return menu;
	}
	
}