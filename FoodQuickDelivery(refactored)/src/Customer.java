//Below i am creating a Customer Class with 6 attributes and a Customer constructor.
public class Customer {

  int orderNumber;
  String customerName;
  String contactNumber;
  String customerAddress;
  String customerLocation;
  String customerEmail;

  public Customer(int orderNumber, String customerName, String contactNumber, String customerAddress,
      String customerLocation, String customerEmail) {

    this.orderNumber = orderNumber;
    this.contactNumber = contactNumber;
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.customerLocation = customerLocation;
    this.customerEmail = customerEmail;

  }

}
