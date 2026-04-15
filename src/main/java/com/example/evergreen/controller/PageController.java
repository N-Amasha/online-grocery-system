package com.example.evergreen.controller;

import com.example.evergreen.model.Product;
import com.example.evergreen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home(Model model) {
        // Fetch all products for the home page
        List<Product> products = productRepository.findAll();
        model.addAttribute("listProducts", products);
        return "index";
    }

    @GetMapping("/curated-collection")
    public String showCuratedPage(Model model) {
        // Fetch the same list for the dedicated curated page
        List<Product> products = productRepository.findAll();
        model.addAttribute("listProducts", products);

        // This will look for curated.html in src/main/resources/templates
        return "curated";
    }
}