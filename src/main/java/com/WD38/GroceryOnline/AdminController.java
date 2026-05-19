package com.WD38.GroceryOnline;

import java.io.IOException;
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

import com.WD38.GroceryOnline.Builders.NotificationBuilder;
import com.WD38.GroceryOnline.JsonCRUD.JSONHandler;
import com.WD38.GroceryOnline.ProductStuff.AdminProductOverviewer;
import com.WD38.GroceryOnline.ProductStuff.Product;
import com.WD38.GroceryOnline.apibodies.ProductQuantityTemplate;
import com.WD38.GroceryOnline.orderstuff.Order;
import com.WD38.GroceryOnline.utils.ProcessOrdersToView;

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
public class AdminController {
	static final String CONFIG_PATH_ABSOLUTE = "/home/meeee/Desktop/projects/uni stuff/project grocery/admin dashboard/backend/GroceryOnline (1)/GroceryOnline/src/main/resources/adminConfig.json";
	static final String ORDERS_REPOSITORY_PATH = "src/main/resources/orders.json";
	static final Admin admin = new Admin(CONFIG_PATH_ABSOLUTE);
	static AdminProductOverviewer productOverviewer = new AdminProductOverviewer();

    private final JSONHandler<Order> orderHandler;

    public AdminController() throws IOException {
        this.orderHandler = new JSONHandler<>(
                ORDERS_REPOSITORY_PATH,
                Order.class
        );
    }

	@RequestMapping("/admin/notifications")
	public HashMap<String, ArrayList<HashMap<String, Object>>> notifications() {
		// these should be changable via UI

		NotificationBuilder notificationBuilder = new NotificationBuilder();
		
		ArrayList<Product> runOutProducts = productOverviewer.lowStock(admin.getQuantityThreshold());
		ArrayList<Product> ExpryProducts = productOverviewer.closeToExpiry(admin.getExpiryThreshold());

		for (Product product: runOutProducts)
			notificationBuilder.addCriticallyLowNotification(product.getName(), product.getQuantity());
		for (Product product: ExpryProducts){
			long daysTillExpiry = ChronoUnit.DAYS.between(LocalDate.now(), product.getExpDate());
			notificationBuilder.addExpirationNotification(product.getName(), daysTillExpiry);
		}

		return notificationBuilder.getNotificationsMap();
	}

	@RequestMapping("/admin/customer_overview")
	public OrderStatusTotal customerOverview(
		@RequestParam(defaultValue="today") String timeFrame 
	) throws IOException{
		
		ArrayList<Order> allOrders = this.orderHandler.readAll();
		ArrayList<Order> customerOrders = ProcessOrdersToView.filterOrders(allOrders, true, false);
		OrderStatusTotal out = OrderHelpers.getObjOrderOverview(customerOrders);
		return out;
	}

	@RequestMapping("/admin/product_overview")
	public OrderStatusTotal productOverview(
		@RequestParam(defaultValue="today") String timeFrame 
	) throws IOException{

		ArrayList<Order> allOrders = this.orderHandler.readAll();
		ArrayList<Order> customerOrders = ProcessOrdersToView.filterOrders(allOrders, false, false);
		OrderStatusTotal out = OrderHelpers.getObjOrderOverview(customerOrders);
		return out;
	}

	@RequestMapping("/admin/thresholds/set")
	public HashMap<String, Object> setThresholds(
		@RequestParam int expiry,
		@RequestParam int quantity
	) 
	{
		HashMap<String, Object> response = new HashMap<>();
		
		int updateStatus = admin.setThresholds(expiry, quantity);
		int statusCode = (updateStatus==0)? 200: (updateStatus==-1)? 422: 500;
		
		response.put("code", statusCode);
		return response;
	}

	@RequestMapping("/admin/thresholds/get")
	public HashMap<String, Object> sendThresholsValues() {
		HashMap<String, Object> out = new HashMap<>();
		out.put("quantity", admin.getQuantityThreshold());
		out.put("expiry", admin.getExpiryThreshold());
		return out;
	}

	@RequestMapping("admin/products/quantities/pure")
	public ArrayList<ProductQuantityTemplate> sendProductQuantityData() {
		ArrayList<Product> rawProducts = productOverviewer.viewAllProducts();
		ArrayList<ProductQuantityTemplate> out = AdminProductOverviewer.generateProductQuantityView(rawProducts);
		return out;
	}

	// @RequestMapping("admin/products/quantities/withOrders")
	// public static ArrayList<ProductQuantityTemplate> sendPromisedProductQuantitData() {
	// 	ArrayList<Product> rawProducts = productOverviewer.viewAllProducts();
		
	// }


	public static void main(String[] args) {
		SpringApplication.run(AdminController.class, args);
	}

}
