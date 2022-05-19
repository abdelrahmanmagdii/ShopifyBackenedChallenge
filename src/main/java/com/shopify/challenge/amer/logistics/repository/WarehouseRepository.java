package com.shopify.challenge.amer.logistics.repository;
import com.shopify.challenge.amer.logistics.dto.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WarehouseRepository  extends JpaRepository<Warehouse, Integer>
{
    List<Warehouse> findAll();

}
