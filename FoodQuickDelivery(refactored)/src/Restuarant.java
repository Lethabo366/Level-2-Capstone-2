import java.util.ArrayList;

//Below I am creating a restaurant class with 8 attributes and a Restuarant constructor.
public class Restuarant {
  String restuarantName;
  String restuarantLocation;
  String restuarantContactNumber;
  ArrayList<Integer> numberOfMeals;
  ArrayList<String> orderName;
  ArrayList<Double> orderPrices;
  String specialInstructions;
  double totalAmountOrder;

  public Restuarant(String restuarantName, String restuarantLocation, String restuarantContactNumber,
      ArrayList<Integer> numberOfMeals, ArrayList<String> orderName, ArrayList<Double> orderPrices,
      String specialInstructions, double totalAmountOrder) {

    this.restuarantName = restuarantName;
    this.restuarantLocation = restuarantLocation;
    this.restuarantContactNumber = restuarantContactNumber;
    this.numberOfMeals = numberOfMeals;
    this.orderName = orderName;
    this.orderPrices = orderPrices;
    this.specialInstructions = specialInstructions;
    this.totalAmountOrder = totalAmountOrder;

  }

}
