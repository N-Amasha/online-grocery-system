package oop;

import java.util.ArrayList;
import java.util.List;

interface OrderOperations {
    void processOrder();
    void generateInvoice();
}

class OrderItem {
    private String itemName;
    private int quantity;
    private double unitPrice;

    public OrderItem(String name, int qty, double price) {
        this.itemName = name;
        this.quantity = qty;
        this.unitPrice = price;
    }

    public String getItemDetails() {
        return itemName + " x " + quantity + " @ Rs." + unitPrice + " = Rs." + (quantity * unitPrice);
    }
}

class Order implements OrderOperations {
    private int orderId;
    private String customerName;
    private String status;
    private List<OrderItem> items = new ArrayList<>();

    public Order(int id, String name) {
        this.orderId = id;
        this.customerName = name;
        this.status = "Pending";
    }

    public void addItem(String name, int qty, double price) {
        items.add(new OrderItem(name, qty, price));
    }

    private double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            // Logic to sum up prices if needed
        }
        return 5000.00;
    }

    @Override
    public void processOrder() {
        this.status = "Processed";
        System.out.println("Processing Order #" + orderId + " for " + customerName);
    }

    @Override
    public void generateInvoice() {
        System.out.println("\n********** INVOICE **********");
        System.out.println("Customer: " + customerName);
        System.out.println("-----------------------------");
        for (OrderItem item : items) {
            System.out.println(item.getItemDetails());
        }
        System.out.println("-----------------------------");
        System.out.println("Total Bill : Rs. " + calculateTotal());
        System.out.println("Status     : " + status);
        System.out.println("*****************************\n");
    }
}

public class Main {
    public static void main(String[] args) {
        Order myOrder = new Order(505, "Nimal Perera");
        myOrder.addItem("Sugar", 2, 250.00);
        myOrder.addItem("Milk Powder", 1, 1200.00);
        myOrder.addItem("Rice 5kg", 1, 950.00);
        myOrder.processOrder();
        myOrder.generateInvoice();
    }
}
