package com.shopify.challenge.amer.logistics.service;
import com.shopify.challenge.amer.logistics.dto.ProductWarehouse;
import com.shopify.challenge.amer.logistics.repository.ProductWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductWarehouseService {
    @Autowired
    ProductWarehouseRepository productWarehouseRepository;

    //getting all product records
    public List<ProductWarehouse> getAllProductWarehouses()
    {
        List<ProductWarehouse> productWarehouses = new ArrayList<>();
        productWarehouseRepository.findAll().forEach(productwarehouse -> productWarehouses.add(productwarehouse));
        return productWarehouses;
    }
    //getting a specific record
    public ProductWarehouse getProductWarehouseByID(ProductWarehouse pw)
    {
        Example<ProductWarehouse> example = Example.of(pw);
        Optional<ProductWarehouse> result = productWarehouseRepository.findOne(example);
        if(result.isPresent())
            return result.get();
        else
            return null;
    }
    public void saveOrUpdate(ProductWarehouse product_warehouse)
    {
        productWarehouseRepository.save(product_warehouse);
    }
    //deleting a specific record
    public void delete(int id)
    {
        productWarehouseRepository.deleteById(id);
    }
}
