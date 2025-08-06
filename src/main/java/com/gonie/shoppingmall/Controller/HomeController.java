package com.gonie.shoppingmall.Controller;

import com.gonie.shoppingmall.model.Product;
import com.gonie.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Arrays;



@RestController // Marks this class as a REST API controller
public class HomeController {
    @Autowired
    private ProductService service;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Gonie welcome";
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product)
    {
        return service.addProduct(product);
    }

}





