package com.shopify.challenge.amer.logistics.service;

import com.shopify.challenge.amer.logistics.dto.Product;
import com.shopify.challenge.amer.logistics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //getting all product records
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }
    //getting a specific record
    public Product getProductByID(int id)
    {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            return null;
    }

    public Product saveOrUpdate(Product product)
    {
       Product p = productRepository.save(product);
       return p;
    }
    //deleting a specific record
    public void delete(int id)
    {
        productRepository.deleteById(id);
    }
}
