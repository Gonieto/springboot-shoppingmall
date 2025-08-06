package com.gonie.shoppingmall.service;

import com.gonie.shoppingmall.repository.ProductRepository;
import com.gonie.shoppingmall.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts()
    {
        return repo.findAll();
    }

    public Product getProductById(int id)
    {
        return repo.findById(id);
    }

    public Product addProduct(Product product)
    {
        return repo.save(product);
    }
}
