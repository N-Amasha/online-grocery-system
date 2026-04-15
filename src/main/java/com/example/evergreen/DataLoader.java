package com.example.evergreen;

import com.example.evergreen.model.Product;
import com.example.evergreen.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repository;

    public DataLoader(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) { // Only add if DB is empty
            Product p1 = new Product();
            p1.setName("Organic Kale");
            p1.setCategory("Vegetables");
            p1.setPrice(4.99);
            p1.setStockQuantity(50);
            p1.setDescription("Nutrient-rich green kale.");

            repository.save(p1);
            System.out.println("Sample data loaded successfully!");
        }
    }
}