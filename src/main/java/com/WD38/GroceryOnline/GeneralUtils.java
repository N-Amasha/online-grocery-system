package com.WD38.GroceryOnline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.HashMap;

public class GeneralUtils {
    public static LocalDate getDateTimeObj(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public static void main(String[] args) {
        // HashMap<String, Integer> test_orderOrverview = getOrderOverview(CustomerOrders.readAll());
        // System.out.println(test_orderOrverview);
    }
}
