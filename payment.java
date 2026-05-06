import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class payment {

    private String userId;
    private String orderId;
    private double amount;
    private String status;

    public payment(String orderId, double amount){
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = "PENDING";
    }

    public String getuserId(){
        return userId;
    }
    public String getOrderId(){
        return orderId;
    }
    public double getAmount(){
        return amount;
    }
    public String getstatus(){
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public void displayDetail(){
        System.out.println("User ID: "+ userId);
        System.out.println("Order ID: "+ orderId);
        System.out.println("Amount: Rs. "+ amount);
        System.out.println("Status: "+ status);
    }

    public void processPayment(){
        System.out.println("Processing payment...");
        System.out.println("Amount: Rs. "+ amount);
    }
}
