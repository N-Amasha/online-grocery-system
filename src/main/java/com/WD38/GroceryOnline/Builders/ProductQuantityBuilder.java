package com.WD38.GroceryOnline.Builders;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductQuantityBuilder {
    private static ArrayList<HashMap<String, Object>> out = new ArrayList<>();

    public static void addProductEntry(String name, int currentQuantity, int withOrdersQuantity) {
        HashMap<String, Integer> quantities = new HashMap<>();
        quantities.put("withoutOrders", currentQuantity);
        quantities.put("withOrders", withOrdersQuantity);

        HashMap<String, Object> productEntry = new HashMap<>();
        productEntry.put("name", name);
        productEntry.put("quantity", quantities);

        out.add(productEntry);
        System.out.println("Log: product added");
    }

    public static ArrayList<HashMap<String, Object>> getProductEntries() {
        return out;
    }

    public static void clearProductList(){
        out.clear();
        System.out.println("Log: product list cleared");
    }
    
}
