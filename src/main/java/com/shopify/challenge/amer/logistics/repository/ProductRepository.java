package com.shopify.challenge.amer.logistics.repository;
import com.shopify.challenge.amer.logistics.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository  extends JpaRepository<Product, Integer>
{
    List<Product> findAll();

}
