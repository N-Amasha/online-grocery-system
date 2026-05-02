package com.grocery.product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManager pm = new ProductManager();

        while (true) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Update Product");
            System.out.println("6. Low Stock");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                System.out.print("ID: ");
                String id = sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Category: ");
                String category = sc.nextLine();

                System.out.print("Price: ");
                double price = sc.nextDouble();

                System.out.print("Quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine();

                Product product = new Product(id, name, category, price, quantity);
                pm.addProduct(product);

            } else if (ch == 2) {
                pm.viewProducts();

            } else if (ch == 3) {
                System.out.print("Enter Product ID: ");
                String id = sc.nextLine();
                pm.searchProduct(id);

            } else if (ch == 4) {
                System.out.print("Enter Product ID: ");
                String id = sc.nextLine();
                pm.deleteProduct(id);

            } else if (ch == 5) {
                System.out.print("Enter Product ID: ");
                String id = sc.nextLine();

                System.out.print("New Name: ");
                String name = sc.nextLine();

                System.out.print("New Category: ");
                String category = sc.nextLine();

                System.out.print("New Price: ");
                double price = sc.nextDouble();

                System.out.print("New Quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine();

                pm.updateProduct(id, name, category, price, quantity);

            } else if (ch == 6) {
                pm.lowStock();

            } else if (ch == 7) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}