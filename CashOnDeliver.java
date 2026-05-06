public class CashOnDeliver  extends payment {
    private String deliberyAddress;
    private String phoneNumber;

    public CashOnDeliver(String OrderId,double amount,String deliberyAddress,String phoneNumber){
        super(orderId,amount);
        this.deliberyAddress=deliberyAddress;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing COD payment: " + getOrderId());
        System.out.println("Delivery Address: " + deliberyAddress);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Amount to pay on delivery: Rs." + getAmount());
        setstatus("Pending Delivery");
        System.out.println("Order Confirmed!");
    }
}
