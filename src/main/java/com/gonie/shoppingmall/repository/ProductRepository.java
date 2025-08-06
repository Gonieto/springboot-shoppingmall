package com.gonie.shoppingmall.repository;

import com.gonie.shoppingmall.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
