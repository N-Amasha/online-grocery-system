package com.WD38.GroceryOnline;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

		ArrayList<String[]> all_products = Products.readAll(); 

		for (String[] row: all_products) {
			// quantity check
			int current_quantity = Integer.parseInt(row[Products.QUANTITY_IND]); 
			if (current_quantity <= QUANTITY_THRESHOLD) {
				notificationBuilder.addCriticallyLowNotification(row[0], current_quantity);
			}
			// expiry check
			LocalDate item_expiryDate = GeneralUtils.getDateTimeObj(row[Products.EXP_DATE]);
			long days_till_expiry = ChronoUnit.DAYS.between(LocalDate.now(), item_expiryDate);
			if (days_till_expiry <= EXPIRY_THRESHOLD) {
				notificationBuilder.addExpirationNotification(row[0], days_till_expiry);
			}
		}
		

		// NotificationBuilder notification_Builder = new NotificationBuilder();

		// notification_Builder.addRunOutNotification("Tomato");
		// notification_Builder.addRunOutNotification("Cabbage");
		// notification_Builder.addExpirationNotification("Papaya", 15);

		return notificationBuilder.getNotificationsMap();
	}

	@RequestMapping("/admin/overview")
	public static HashMap<String, HashMap<String, Integer>> overview(
		@RequestParam(defaultValue="today") String timeFrame 
	) {

		Function<ArrayList<HashMap<String, Object>>, 
		HashMap<String, Integer>> getOrderOverview = raw_order_data -> {
			HashMap<String, Integer> orderOverview = new HashMap<>(Map.of(
				"packing", 0,
				"in-transit", 0,
				"received", 0,
				"cancelled", 0
			));

			for (HashMap<String, Object> row : raw_order_data) {
				String order_status = "";
				Object value = row.get("orderStatus");

				if (value instanceof String string) {
					order_status = string;
				}

				switch (order_status) {
					case "packing" -> orderOverview.put("packing", orderOverview.get("packing") + 1);
					case "in-transit" -> orderOverview.put("in-transit", orderOverview.get("in-transit") + 1);
					case "received" -> orderOverview.put("received", orderOverview.get("received") + 1);
					case "cancelled" -> orderOverview.put("cancelled", orderOverview.get("cancelled") + 1);
					default -> {}
				}
			}

			return orderOverview;
		};

		//  customer_orders = new HashMap<>();
		HashMap<String, Integer> product_orders = new HashMap<>();
		HashMap<String, Integer> customer_orders = getOrderOverview.apply(CustomerOrders.readAll());

		// --------------------------------------------
		// maybe shipped -> in-transit

		// customer_orders.put("packing", 10);
		// customer_orders.put("shipped", 10);
		// customer_orders.put("received", 10);
		// customer_orders.put("cancelled", 10);

		product_orders.put("packing", 10);
		product_orders.put("shipped", 10);
		product_orders.put("received", 10);
		product_orders.put("cancelled", 10);

		// above should be found using other people stuff

		HashMap<String, HashMap<String, Integer>> out = new HashMap<>();
		out.put("customer_orders", customer_orders);
		out.put("product_orders", product_orders);
		
		return out;
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
