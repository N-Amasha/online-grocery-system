package com.WD38.GroceryOnline.ProductStuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
// import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ProductManager {

    // private String url;
    // private String username;
    // private String password;     
    
    // private Connection con; 

    // public ProductManager(
    //         @Value("${spring.datasource.url}") String url,
    //         @Value("${spring.datasource.username}") String username,
    //         @Value("${spring.datasource.password}") String password        
    // ) {
    

    //     System.out.print(this.url + ' ' + this.username + ' ' + this.password);

    //     try {
    //         this.con = DriverManager.getConnection(
    //                 this.url,
    //                 this.username,
    //                 this.password
    //         );
    //     } catch (Exception e) {
    //         System.out.println("Database connection failed!");
    //         System.out.println(e.getMessage());
    //     }        
    // }

    public void addProduct(Product p) {
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, p.getId());
            pst.setString(2, p.getName());
            pst.setString(3, p.getCategory());
            pst.setDouble(4, p.getPrice());
            pst.setInt(5, p.getQuantity());

            pst.executeUpdate();
            System.out.println("Product added successfully!");

        } catch (Exception e) {
            System.out.println("Add product failed!");
            System.out.println(e.getMessage());
        }
    }

    public void viewProducts() {
        String sql = "SELECT * FROM products";

        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("ID | Name | Category | Price | Quantity");
            System.out.println("----------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getString("ID") + " | " +
                                rs.getString("Name") + " | " +
                                rs.getString("Category") + " | Rs." +
                                rs.getDouble("Price") + " | " +
                                rs.getInt("Quantity")
                );
            }

        } catch (Exception e) {
            System.out.println("View products failed!");
            System.out.println(e.getMessage());
        }
    }

    public void searchProduct(String id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getString("product_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("category") + " | Rs." +
                                rs.getDouble("price") + " | " +
                                rs.getInt("quantity")
                );
            } else {
                System.out.println("Product not found!");
            }

        } catch (Exception e) {
            System.out.println("Search product failed!");
            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct(String id) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found!");
            }

        } catch (Exception e) {
            System.out.println("Delete product failed!");
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(String id, String name, String category, double price, int qty) {
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ? WHERE product_id = ?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, category);
            pst.setDouble(3, price);
            pst.setInt(4, qty);
            pst.setString(5, id);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found!");
            }

        } catch (Exception e) {
            System.out.println("Update product failed!");
            System.out.println(e.getMessage());
        }
    }    

    public void lowStock() {
        String sql = "SELECT * FROM products WHERE quantity < 5";

        try {
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            boolean found = false;

            while (rs.next()) {
                System.out.println(
                        "Low Stock -> " +
                                rs.getString("product_id") + " | " +
                                rs.getString("name") + " | Qty: " +
                                rs.getInt("quantity")
                );
                found = true;
            }

            if (!found) {
                System.out.println("No low stock products.");
            }

        } catch (Exception e) {
            System.out.println("Low stock check failed!");
            System.out.println(e.getMessage());
        }

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

    public static void main(String[] args) {
        // myProductManager.viewProducts();
        ProductManager myProductManager = new ProductManager();
        ArrayList<Product> qList = myProductManager.lowStock(8);
        ArrayList<Product> expList = myProductManager.closeToExpiry(14);

        System.out.println("About to run out:");
        for (Product item: qList)
            item.displayDetails();

        System.out.println("\nAbout to expire:");
        for (Product item: expList)
            item.displayDetails();
    }
}