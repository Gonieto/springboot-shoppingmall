package com.gonie.shoppingmall.Controller;

import com.gonie.shoppingmall.model.Product;
import com.gonie.shoppingmall.repository.ProductRepository;
import com.gonie.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Arrays;



@RestController // Marks this class as a REST API controller
public class HomeController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repo;

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
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        try {
            Product savedProduct = service.addProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product: " + e.getMessage());
        }
    }

    @DeleteMapping("/productDelete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.ok("Deleted product with ID " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product: " + e.getMessage());
        }
    }

}






