package com.WD38.GroceryOnline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// more mock data
public class CustomerOrders {
    public static ArrayList<HashMap<String, Object>> readAll() {
        ArrayList<HashMap<String, Object>> out = new ArrayList<>();
        out.add(new HashMap<>(Map.of("name", "J.J. Johnes", "orderStatus", "in-transit")));
        out.add(new HashMap<>(Map.of("name", "Alice Johnson", "orderStatus", "packing")));
        out.add(new HashMap<>(Map.of("name", "Brian Smith", "orderStatus", "in-transit")));
        out.add(new HashMap<>(Map.of("name", "Catherine Lee", "orderStatus", "received")));
        out.add(new HashMap<>(Map.of("name", "Daniel Brown", "orderStatus", "cancelled")));
        out.add(new HashMap<>(Map.of("name", "Emma Davis", "orderStatus", "packing")));
        out.add(new HashMap<>(Map.of("name", "Frank Wilson", "orderStatus", "in-transit")));
        out.add(new HashMap<>(Map.of("name", "Grace Miller", "orderStatus", "received")));
        out.add(new HashMap<>(Map.of("name", "Henry Moore", "orderStatus", "cancelled")));
        out.add(new HashMap<>(Map.of("name", "Isabella Taylor", "orderStatus", "packing")));
        out.add(new HashMap<>(Map.of("name", "Jack Anderson", "orderStatus", "in-transit")));

        return out;
    }
}
