package com.WD38.GroceryOnline;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WD38.GroceryOnline.ProductStuff.Product;
import com.WD38.GroceryOnline.ProductStuff.ProductManager;


/* How to run this/ launch on localhost 8080:

requiraments: 
@SpringBootApplication
SpringApplication.run

how to:
just run the java.
(right click the public class in here and run java works)
			  

*/


@SpringBootApplication
@CrossOrigin(origins = "*")    // without this, javascript will get nothing
@RestController
public class GroceryOnlineApplication {

	@RequestMapping("/admin/notifications")
	public static HashMap<String, ArrayList<HashMap<String, Object>>> notifications() {
		// these should be changable via UI
		final int QUANTITY_THRESHOLD = 20;
		final int EXPIRY_THRESHOLD = 15;

		NotificationBuilder notificationBuilder = new NotificationBuilder();
		ProductManager productManager = new ProductManager();

		ArrayList<Product> runOutProducts = productManager.lowStock(QUANTITY_THRESHOLD);
		ArrayList<Product> ExpryProducts = productManager.closeToExpiry(EXPIRY_THRESHOLD);

		for (Product product: runOutProducts)
			notificationBuilder.addCriticallyLowNotification(product.getName(), product.getQuantity());
		for (Product product: ExpryProducts){
			long daysTillExpiry = ChronoUnit.DAYS.between(LocalDate.now(), product.getExpDate());
			notificationBuilder.addExpirationNotification(product.getName(), daysTillExpiry);
		}
		// ArrayList<String[]> all_products = Print oducts.readAll(); 

		// for (String[] row: all_products) {
		// 	// quantity check
		// 	int current_quantity = Integer.parseInt(row[Products.QUANTITY_IND]); 
		// 	if (current_quantity <= QUANTITY_THRESHOLD) {
		// 		notificationBuilder.addCriticallyLowNotification(row[0], current_quantity);
		// 	}
		// 	// expiry check
		// 	LocalDate item_expiryDate = GeneralUtils.getDateTimeObj(row[Products.EXP_DATE]);
		// 	long days_till_expiry = ChronoUnit.DAYS.between(LocalDate.now(), item_expiryDate);
		// 	if (days_till_expiry <= EXPIRY_THRESHOLD) {
		// 		notificationBuilder.addExpirationNotification(row[0], days_till_expiry);
		// 	}
		// }
		

		// NotificationBuilder notification_Builder = new NotificationBuilder();

		// notification_Builder.addRunOutNotification("Tomato");
		// notification_Builder.addRunOutNotification("Cabbage");
		// notification_Builder.addExpirationNotification("Papaya", 15);

		return notificationBuilder.getNotificationsMap();
	}

	@RequestMapping("/admin/customer_overview")
	public static OrderStatusTotal customerOverview(
		@RequestParam(defaultValue="today") String timeFrame 
	) {
		OrderStatusTotal customerOrderStatus = new OrderStatusTotal();

		customerOrderStatus.setPacking(10);
		customerOrderStatus.setinTransit(25);
		customerOrderStatus.setReceived(28);
		customerOrderStatus.setCancelled(3);

		return customerOrderStatus;
	}

	@RequestMapping("/admin/product_overview")
	public static OrderStatusTotal productOverview(
		@RequestParam(defaultValue="today") String timeFrame 
	) {
		OrderStatusTotal productOrderStatus = new OrderStatusTotal();

		productOrderStatus.setPacking(3);
		productOrderStatus.setinTransit(5);
		productOrderStatus.setReceived(8);
		productOrderStatus.setCancelled(1);

		return productOrderStatus;
	}

	// this might be mine
	// @RequestMapping("/admin/product_orders")
	// public static HashMap<String, Object> product_orders(
	// 	@RequestParam(defaultValue = "all") String status,
	// 	@RequestParam(defaultValue = "all") String timeFrame,
	// 	@RequestParam(defaultValue = "all") String type
	// ) {
	// 	HashMap<String, Object> out = new HashMap<>();
	// 	ArrayList<Object> customer_list = new ArrayList<>();

	// 	HashMap<String, Object> item1 = new HashMap<>();
	// 	item1.put("profile", "default");
	// 	item1.put("type", "diary");
	// 	item1.put("name", "Kotmale");
	// 	item1.put("date", "18/05/26");
	// 	item1.put("time", "09:30");
	// 	item1.put("status", "delivered");
	// 	item1.put("additional", new HashMap<String, Object>());

	// 	HashMap<String, Object> item2 = new HashMap<>();
	// 	item2.put("profile", "default");
	// 	item2.put("name", "Kist");
	// 	item2.put("date", "18/05/26");
	// 	item2.put("time", "11:45");
	// 	item2.put("status", "in-trasit");
	// 	item2.put("additional", new HashMap<String, Object>());

	// 	customer_list.add(item1);
	// 	customer_list.add(item2);
	// 	out.put("customer_orders", customer_list);

	// 	return out;
	// }


	public static void main(String[] args) {
		SpringApplication.run(GroceryOnlineApplication.class, args);
	}

}
