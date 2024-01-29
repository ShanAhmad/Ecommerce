package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/greeting")
    public String HelloMessage(@RequestParam String name){
        return "Hello "+name;
    }
    @GetMapping("/product/category/list")
    public List<String> Products(){
        List<String> products = new ArrayList<>();
        products.add("Electronics and Gadgets");
        products.add("Fashion and Apparel");
        products.add("Home and Furniture");
        products.add("Beauty and Personal Care");
        products.add("Books and Media");
        products.add("Health and Wellness");
        products.add("Sports and Outdoors");
        products.add("Toys and Games");
        products.add("Groceries and Food");
        products.add("Automotive and Tools");
        products.add("Jewelry and Accessories");
        products.add("Pet Supplies");
        products.add("Office Supplies");
        products.add("Crafts and DIY");
        products.add("Subscription Boxes");

        return products;
    }
}
