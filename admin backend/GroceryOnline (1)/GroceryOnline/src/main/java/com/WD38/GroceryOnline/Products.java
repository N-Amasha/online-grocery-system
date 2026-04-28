package com.WD38.GroceryOnline;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

// all mock data btw
public class Products {
    public static final int QUANTITY_IND = 2;
    public static final int EXP_DATE = 3;

    public static ArrayList<String[]> readAll() {
        ArrayList<String[]> mock_out = new ArrayList<>();

        mock_out.add(new String[]{"Tomato", "vegetables", "12", "10/05/2026"});
        mock_out.add(new String[]{"Cabbage", "vegetables", "30", "30/04/2026"});

        return mock_out;
    }

    public static void main(String[] args) {
        // The input strings
        String date1Str = "01/05/2026";
        String date2Str = "28/04/2026";

        // Define the format: Day/Month/Year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the strings into LocalDate objects
        LocalDate date1 = LocalDate.parse(date1Str, formatter);
        LocalDate date2 = LocalDate.parse(date2Str, formatter);    
        
        long daysDiff = ChronoUnit.DAYS.between(date2, date1);

        System.out.println(daysDiff);
    }
}
