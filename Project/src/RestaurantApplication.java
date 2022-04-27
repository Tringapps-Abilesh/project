/*In this both the owner and the customer can see the menu ,
 owner can add and delete the food items in the menu and alter the foods in the menu. 
 The customers can view menu and can oder the foods and increase the quantity if they needed
 Then finally the bill with total amount with GSTTAX will be shown to the customer */

import java.util.*;

class Operation {
//Customer's side operations
	public static void customeropeartion(CustomerPage customer) {
		Scanner sc = new Scanner(System.in);
		boolean customerLogin = true;
		while (customerLogin) {
			System.out.println("*******Please select your choice*******");
			System.out.println("1) Display Menu \n" 
					+ "2) Order Food Item \n" 
					+ "3) Update Food Item Quantity\n"
					+ "4) Cancel Food Item \n" 
					+ "5) Display Order\n" 
					+ "6) Pay Bill\n" 
					+ "7) Exit\n");
		//Initializing fooditems.	
			String fooditem = "";
		//Initializing food's quantity.	
			int quantity = 0;
		//getting input from customer.
			byte choice = sc.nextByte();
			switch (choice) {
			//displaying the items in the menu.
			case 1:
				System.out.println("********MENU********");
				customer.displaymenu();
				break;
			//Ordering the food.
			case 2:
				String continue_again="yes";
				while(continue_again=="yes")
				{
				System.out.println("Enter the food item to add.");
				fooditem = sc.next();
				System.out.println("Enter the " + fooditem + "'s quantity.");
				quantity = sc.nextInt();
				if (customer.orderfood(fooditem, quantity))
					System.out.println(quantity + " " + fooditem + " added in the order.");
				else
					System.out.println(fooditem + " does not exist in the menu.");
				System.out.println("Do you want to continue the ordering food? Please Enter 'yes'");
				System.out.println("Otherwise Enter 'no'.");
				continue_again = sc.next();
				}
				break;
			//Increase or Decrease the food quantity.
			case 3:
				System.out.println("Enter the food to update quantity.");
				fooditem = sc.next();
				System.out.println("Enter the food to update quantity.");
				quantity = sc.nextInt();
				if (customer.update(fooditem, quantity))
					System.out.println(quantity + " " + fooditem + " added in the order.");
				else
					System.out.println("Failed to update the quantity for " + fooditem);
				break;
				
				//Remove foods from the menu.
			case 4:
				System.out.println("Enter the food item to remove.");
				fooditem = sc.next();
				if (customer.removefood(fooditem))
					System.out.println(fooditem + " removed from the order");
				else
					System.out.println("Failed to remove the " + fooditem);
				break;
				
			//Display the customer's order with price(preview).
			case 5:
				System.out.println("Your order is ");
				customer.displayorder();
				break;
			//Display the customer's total bill amount including the taxs.
			case 6:
				System.out.println("Your total amount for the following order ");
				System.out.println("************************************************");
				System.out.println("FOOD\tQUANTITY");
				System.out.println("************************************************");
				customer.displayorder();
				System.out.println("The Total Amount(including GSTTAX) = " + customer.totalbill());
				System.out.println("************************************************\n");
				return;
			case 7:
				System.out.println("Exiting from the Customer Module.");
				System.out.println("************************************************");
				customerLogin = false;
				return;
			default:
				System.out.println("Exiting from the Customer Module.");
				System.out.println("************************************************");
				customerLogin = false;
			}
		}
		sc.close();
	}

//Owner's operations
	public static void owneroperation(OwnerPage owner) {
		Scanner sc = new Scanner(System.in);
		boolean ownerLogin = true;
		while (ownerLogin) {
			System.out.println("\n********OPERATIONS FOR OWNER********");
			System.out.println("Enter choice");
			System.out.println("1) Add Food Item.\n"
					+ "2) Remove Food Item \n" 
					+ "3) Update Food Item \n"
					+ "4) Display Menu\n" 
					+ "5) Exit");
			byte choice = sc.nextByte();
			String fooditem = "";
			float price = 0F;
			switch (choice) {
			//Adding food items to the menu,with their price amount.
			case 1:
				System.out.println("Enter the number of food items to be added in the menu.");
				int no_of_items = sc.nextInt();
				for (int i = 0; i < no_of_items; i++) {
					System.out.println("Enter the food item " + (i + 1));
					fooditem = sc.next();
					System.out.println("Enter the food item's price.");
					price = sc.nextFloat();
					if (owner.additem(fooditem, price))
						System.out.println(fooditem + " added in the menu.");
					else
						System.out.println(fooditem + " already in the menu.");
					owner.additem(fooditem, price);
				}

				break;
			//Delete  food from the menu by owner.
			case 2:
				System.out.println("Enter the food item to remove.");
				fooditem = sc.next();
				if (owner.deleteitem(fooditem))
					System.out.println(fooditem + " removed from the menu.");
				else
					System.out.println(fooditem + " does not exist in the menu.");
				break;
			//Update the price of any food item
			case 3:
				System.out.println("Enter the food item to update its price.");
				fooditem = sc.next();
				System.out.println("Enter the its price.");
				price = sc.nextFloat();
				if (owner.update(fooditem, price))
					System.out.println("Menu was updated.");
				else
					System.out.println("Failed to update the  menu.");
				break;
			//Displaying the menu.
			case 4:
				owner.displaymenu();
				break;
			case 5:
				System.out.println("Exiting from the Owner Module.");
				System.out.println("************************************************");
				ownerLogin = false;
				return;
			default:
				System.out.println("Exiting from Owner Module.");
				System.out.println("************************************************");
				ownerLogin = false;
			}
		}
		sc.close();
	}

//Main class
public static class RestaurantApplication {
	public static void main(String[] args) {
		OwnerPage owner = new OwnerPage();
		CustomerPage customer = new CustomerPage();
		Scanner sc = new Scanner(System.in);
		boolean want_to_continue = true;
		while (want_to_continue) {
			System.out.println("\n******WELCOME TO ABI RESTAURANT******\n");
			System.out.println("Login as,(Enter 1 for the Owner and 2 for the Customer ) ");
			System.out.println("1) Owner");
			System.out.println("2) Customer");
			System.out.println("3) Exit");

			byte login_as = sc.nextByte();
			switch (login_as) {
			//Owner side login
			case 1:
				System.out.println("*****Welcome to Owner's page*****");
				System.out.println("Enter the username.");
				String name = sc.next();
				System.out.println("Enter the password.");
				String password = sc.next();
				
				if (owner.authorization(name, password))
					Operation.owneroperation(owner);
				else 
					System.out.println("Invalid username or password!!!!.");
				break;
			//Customer side
			case 2:
				Operation.customeropeartion(customer);
				break;
			case 3:
				System.out.println("******Thank You*****");
				return;
			default:
				System.out.println("Invalid Choice!!!! " );
				want_to_continue = false;
			}

		}
		sc.close();
	}
}
}