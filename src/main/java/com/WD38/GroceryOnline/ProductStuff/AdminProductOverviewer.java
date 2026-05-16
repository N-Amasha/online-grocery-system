package com.WD38.GroceryOnline.ProductStuff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.WD38.GroceryOnline.apibodies.ProductQuantityTemplate;

public class AdminProductOverviewer {
    protected Connection productDbConnection;

    public static ArrayList<ProductQuantityTemplate> generateProductQuantityView(ArrayList<Product> productList){
        ArrayList<ProductQuantityTemplate> out = new ArrayList<>();

        for (Product product: productList)
            out.add(new ProductQuantityTemplate(product));

        return out;
    }

    public AdminProductOverviewer() {
        productDbConnection = DatabaseConnection.getConnection();
    }

    public ArrayList<Product> lowStock(int threshold) {
        String sql = "SELECT * FROM products WHERE quantity < " + threshold;
        ArrayList<Product> out = new ArrayList<>();

        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            boolean found = false;

            while (rs.next()) {
                Product product = new Product(
                    rs.getString("ID"), 
                    rs.getString("Name"), 
                    rs.getString("Category"),
                    rs.getDouble("Price"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("ExpDate").toLocalDate());

                out.add(product);
            }

            if (!found) {
                System.out.println("No low stock products.");
            }

        } catch (Exception e) {
            System.out.println("Low stock check failed!");
            System.out.println(e.getMessage());
        }

        return out;

    }

    public ArrayList<Product> closeToExpiry(int days) {
        ArrayList<Product> out = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = futureDate.format(formatter);

        try {
            String sql = "SELECT * FROM products WHERE ExpDate < '" + dateString + "'";
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product(
                    rs.getString("ID"), 
                    rs.getString("Name"), 
                    rs.getString("Category"),
                    rs.getDouble("Price"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("ExpDate").toLocalDate());

                out.add(product);
            }

        } catch (Exception e) {
            System.out.println("expiry check failed!");
            System.out.println(e.getMessage());
        }

        return out;
    }


    public ArrayList<Product> viewAllProducts() {
        ArrayList<Product> out = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("ID | Name | Category | Price | Quantity");
            System.out.println("----------------------------------------");

            while (rs.next()) {
                Product product = new Product(
                    rs.getString("ID"), 
                    rs.getString("Name"), 
                    rs.getString("Category"),
                    rs.getDouble("Price"), 
                    rs.getInt("Quantity"), 
                    rs.getDate("ExpDate").toLocalDate());

                out.add(product);                
            }

        } catch (Exception e) {
            System.out.println("View products failed!");
            System.out.println(e.getMessage());
        }

        return out;
    }
    
}
