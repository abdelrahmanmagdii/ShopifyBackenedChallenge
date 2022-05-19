package com.shopify.challenge.amer.logistics.service;


import com.shopify.challenge.amer.logistics.dto.Warehouse;
import com.shopify.challenge.amer.logistics.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    //getting all warehouse records
    public List<Warehouse> getAllwarehouses()
    {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouseRepository.findAll().forEach(warehouse -> warehouses.add(warehouse));
        return warehouses;
    }
    //getting a specific record
    public Warehouse getWarehouseByID(int id)
    {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            return null;
    }

    public Warehouse saveOrUpdate(Warehouse warehouse)
    {
        Warehouse w = warehouseRepository.save(warehouse);
        return w;
    }
    //deleting a specific record
    public void delete(int id)
    {
        warehouseRepository.deleteById(id);
    }
}
