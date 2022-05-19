package com.shopify.challenge.amer.logistics.repository;
import com.shopify.challenge.amer.logistics.dto.ProductWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductWarehouseRepository  extends JpaRepository<ProductWarehouse, Integer>
{
    List<ProductWarehouse> findAll();

}
