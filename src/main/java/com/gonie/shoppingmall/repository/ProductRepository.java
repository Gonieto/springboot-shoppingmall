package com.gonie.shoppingmall.repository;

import com.gonie.shoppingmall.model.*;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    public List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "MacBook", 3600),
            new Product(2, "IPhone",1200),
            new Product(3, "Samsung Fold 7",1190)
    ));

    public List<Product> findAll()
    {
        return products;
    }

    public Product findById(int id)
    {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

    }

    public Product save(Product product)
    {
        products.add(product);
        return product;
    }
    }

