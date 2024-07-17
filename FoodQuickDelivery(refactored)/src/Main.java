import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;

public class Main {

  public static void main(String[] args) {
    /*
     * Below i am creating 3 arrayLists to store the customer's order , one
     * (numberOfMealsArray the stores the number of each order they intend to order,
     * two (orderNamesArray) stores the name of each order, there (orderPricesArray)
     * stores the order prices.
     */
    ArrayList<Integer> numberOfMealsArray = new ArrayList<Integer>();
    ArrayList<String> orderNamesArray = new ArrayList<>();
    ArrayList<Double> orderPricesArray = new ArrayList<Double>();

    int orderNumber = 1000;

    /*
     * Below the user will be requested to enter information about themselves and
     * the restaurant they ordered from, When the asked for their order they will
     * will be asked about the orderName , orderPrice and the number of meals when
     * they asked if they would like to add to their order and they type the letter
     * "y" the loop will start over and the user will be asked again what they would
     * like to order.
     */

    Scanner input = new Scanner(System.in);
    System.out.println("What is your name?");
    String customerName = input.nextLine();
//		
    System.out.println("What is your Contact Number?");
    String customerContactNumber = input.nextLine();

    System.out.println("What is the address you would like your order to delivered?");
    String customerAddress = input.nextLine();

    System.out.println("Which city do you stay in ?");
    String customerLocation = input.nextLine();

    System.out.println("What is your Email Address ?");
    String customerEmail = input.nextLine();

    System.out.println("What is the Name of the restuarant you would like to order from ?");
    String restuarantName = input.nextLine();

    System.out.println("Which City is the Restuarant Located in?");
    String restuarantLocation = input.nextLine();
    String adding = "";

    /*
     * The do while loop below will ask the user for their order and prices, if the
     * user wants to add another item and their answer is y then, the loop will
     * repeat ask the user for their next order the loop will, continue until they
     * type n or an answer that isn't y the loop will end.
     */

    do {
      System.out.println("What is an item you would like to order?");
      String customerOrderItem = "";
      customerOrderItem = input.nextLine();
      orderNamesArray.add(customerOrderItem);

      System.out.println("What is the price of your order? format (78.00)");
      String customerOrderPriceString = input.nextLine();
      try {
        double customerOrderPriceDouble = Double.parseDouble(customerOrderPriceString);
        orderPricesArray.add(customerOrderPriceDouble);
      } catch (NumberFormatException e) {
        System.out.println("Only Numbers and a decimal point are allowed to be used for the order Prices! ");
      }
      System.out.println("How many of this item would like?");
      int customerNumberOfMeals = input.nextInt();
      numberOfMealsArray.add(customerNumberOfMeals);

      System.out.println("Would you like to add an item to your order List? (y for yes and n for no");
      adding = input.next();
      input.nextLine();
    } while (adding.equals("y"));

    System.out.println("Do you have any Special Requests for your order?");
    String customerSpecialInstructions = input.nextLine();

    System.out.println("What are the restuarants contact Details?");
    String restuarantContactDetails = input.nextLine();
    input.close();

    double totalAmountOrder = calculateTotalAmount(numberOfMealsArray, orderPricesArray);
    System.out.println(totalAmountOrder);

    /*
     * Below i am creating a customer object and restuarant object i will populate
     * these objects with the information the customer/user input and were stored
     * under variables.
     */
    Customer customer1 = new Customer(orderNumber, customerName, customerContactNumber, customerAddress,
        customerLocation, customerEmail);

    Restuarant restuarant1 = new Restuarant(restuarantName, restuarantLocation, restuarantContactDetails,
        numberOfMealsArray, orderNamesArray, orderPricesArray, customerSpecialInstructions, totalAmountOrder);

    System.out.println();

    /*
     * Below i am using the findDriver method to find a driver for the customers.
     * the findDriver method compare the customers location to the drivers
     * available, in the driver.txt file if the driver is available the information
     * about, the restaurant,customer and order will be added to the invoice.
     */

    findDriver(customer1, restuarant1);
  }

  /*
   * Below the calculateTotalAmount ArrayList will take the number of meals and
   * multiply it by the order prices, and add them together to get the amount the
   * customer will pay for the order.
   */

  public static double calculateTotalAmount(ArrayList<Integer> numberOfMealsArray, ArrayList<Double> orderPricesArray) {
    double totalAmount = 0.00;

    /*
     * Below i am using math.round to round off the total amount inorder to round
     * off by two decimals, by two decimals i multiplied the amount by 100 and
     * divided by 100 after the amount has been, rounded off.
     */

    for (int i = 0; i < numberOfMealsArray.size(); i++) {
      totalAmount += Math.round((numberOfMealsArray.get(i) * orderPricesArray.get(i)) * 100) / 100;
    }
    return totalAmount;
  }

  /*
   * Below this method will take the will take the numberOfMeals ArrayList .
   * orderNames ArrayList and the orderPrices ArrayList, and return all the orders
   * as strings to be used when creating an invoice.
   */
  public static String orderAsString(ArrayList<Integer> numberOfMeals, ArrayList<String> orderNames,
      ArrayList<Double> orderPrices) {

    String orderAsString = "";
    /*
     * Below Integer.toString will casts the numberOfMeals value to string and
     * String.valueOf, will cast the orderPrices double values to string, inOrder to
     * add them to the orderAsString variable to be returned.
     */

    for (int i = 0; i < orderNames.size(); i++) {
      String numberOfMealsString = Integer.toString(numberOfMeals.get(i));
      String objectPricesString = String.valueOf(orderPrices.get(i));

      orderAsString += numberOfMealsString + " x " + orderNames.get(i) + " (R" + objectPricesString + ") \n";
    }
    return orderAsString;
  }

  /*
   * Below the invoiceCreate Method will create an invoice.txt file using the
   * customer class object , restaurant class object and idealDriver .
   */

  public static void invoiceCreate(Customer customer, Restuarant restuarant, String idealDriver) {
    /*
     * Below i am storing restuarant.numberOfMeals,restuarant.orderName and
     * restuarant.orderPrices, under their own arrayLists inorder to access the
     * arrayList values that were stored in them.
     */

    ArrayList<Integer> numberOfMeals = restuarant.numberOfMeals;
    ArrayList<String> orderNames = restuarant.orderName;
    ArrayList<Double> orderPrice = restuarant.orderPrices;

    /*
     * Below i am using split to change the idealDriver line of String, into a
     * String array with 3 items the drivers name , drivers location, and the
     * drivers load. Integer.toString will cast the integer variable
     * customer.orderNumber to a, string in order to be stored under the invoice
     * String variable. the String.valueOf will the cast the double variable
     * restuarant.totalAmountOrder, to a string in order to stored under the invoice
     * variable.
     */

    String[] idealDriverArray = idealDriver.split(", ");
    String orderNumberString = Integer.toString(customer.orderNumber);
    String totalString = String.valueOf(restuarant.totalAmountOrder);

    String invoice = "Order Number " + orderNumberString + "\n";
    invoice += "Customer: " + customer.customerName + " \n";
    invoice += "Email: " + customer.customerEmail + "\n";
    invoice += "Phone Number: " + customer.contactNumber + " \n";
    invoice += "Location: " + customer.customerLocation + " \n\n";
    invoice += "You have ordered the following from " + restuarant.restuarantName + " in "
        + restuarant.restuarantLocation + "\n\n";
    invoice += orderAsString(numberOfMeals, orderNames, orderPrice) + "\n";
    invoice += "Special Instructions: " + restuarant.specialInstructions + "\n\n";
    invoice += "Total: R" + totalString + "\n\n";
    invoice += idealDriverArray[0] + " is nearest to the restaurant and so he will be delivering your "
        + "order to you at:\n\n";
    invoice += customer.customerAddress + "\n";
    invoice += customer.customerLocation + "\n\n";
    invoice += "If you need to contact the restaurant, their number is " + restuarant.restuarantContactNumber;

    try {
      Formatter invoiceFile = new Formatter("invoice.txt");
      invoiceFile.format("%s", invoice);
      invoiceFile.close();

      System.out.println("Your order is being delivered!");

    } catch (FileNotFoundException e) {
      System.out.println("The file invoice.txt could not be Created/OverWritten!");
    }
  }

  /*
   * The method below name find driver will get the drivers.txt file and store it
   * under drivers file, then a scanner will go through each driver in the
   * drivers.txt if a driver is in the the same location as the customer the
   * driver will be saved under ideal driver when a driver is found when other
   * drivers are found who live in the same location as the customer their loads
   * will be compared, the driver with the smallest load will be chosen and a
   * invoice will be made if their is no driver in the customers location then the
   * user will get a message in their invoice that says
   * "Sorry! Our drivers are too far away from you to be able to deliver to your location."
   */
  public static void findDriver(Customer customer, Restuarant restuarant) {
    try {
      File driversFile = new File("drivers-info.txt");
      Scanner driversRead = new Scanner(driversFile);

      String idealDriver = "";

      /*
       * Below within the while loop the variables idealDriver and driverline are
       * split into arrays, in order to access just the drivers load and location to
       * be compared.
       */
      while (driversRead.hasNext()) {
        String driverLine = driversRead.nextLine();
        String[] splitDriver = driverLine.split(", ");
        String[] idealDriverArray = idealDriver.split(", ");
        if ((customer.customerLocation.toLowerCase()).equals(splitDriver[1].toLowerCase())) {
          if (idealDriver.equals("")) {
            idealDriver = driverLine;

          } else {

            int idealDriverLoad = Integer.valueOf(idealDriverArray[2]);
            int splitDriverLoad = Integer.valueOf(splitDriver[2]);

            if (idealDriverLoad > splitDriverLoad) {
              idealDriver = driverLine;
            }
          }
        }
      }
      driversRead.close();

      /*
       * if the idealDriver is still a blank string is still after the while loop then
       * it means no driver was, available so then the message stating the drivers
       * were busy will be printed onto the invoice.txt file, and the user will
       * receive a message telling them to check their invoice.
       */
      if (idealDriver.equals("")) {
        String invoice = "Sorry!Our drivers are too far away from you to be able to deliver to your location.";

        try {
          Formatter invoiceFile = new Formatter("invoice.txt");
          invoiceFile.format("%s", invoice);
          invoiceFile.close();

          System.out.println("Check your invoice for details on your delivery!");

        } catch (FileNotFoundException e) {
          System.out.println("The file invoice.txt could not be Created/OverWritten!");
        }
      } else {
        invoiceCreate(customer, restuarant, idealDriver);
      }
    }

    catch (FileNotFoundException e) {
      System.out.println("The file drivers.txt cannot be found!");
    }
  }
}

/*
 * references for help with casting int to string and double to string.
 * https://www.geeksforgeeks.org/how-to-convert-a-string-to-an-int-in-java/
 * https://www.bing.com/search?q=cast%20double%20as%20a%20String%20in%20java&qs=
 * n&form=QBRE&sp=-1&lq=0&pq=cast%20do%20as%20a%20string%20in%20java&sc=3-27&sk=
 * &cvid=FB6A733708D44D7E8F83DAC37DA1BFB3&ghsh=0&ghacc=0&ghpl=&ntref=1
 * 
 */
