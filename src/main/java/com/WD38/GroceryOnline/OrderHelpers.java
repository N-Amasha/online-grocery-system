package com.WD38.GroceryOnline;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHelpers {
   public static HashMap<String, Integer> getOrderOverview(ArrayList<HashMap<String, Object>> raw_order_data) {
        HashMap<String, Integer> orderOverview = new HashMap<>();
		orderOverview.put("packing", 0);
		orderOverview.put("in-transit", 0);
		orderOverview.put("received", 0);
		orderOverview.put("cancelled", 0);      

        for (HashMap<String, Object> row: raw_order_data) {

            String order_status = "";
            Object value = row.get("orderStatus");
            if (value instanceof String string){    
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
    } 
    

}
