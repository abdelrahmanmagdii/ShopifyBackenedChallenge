package com.shopify.challenge.amer.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWarehouseModel {
    private int warehouseId;
    private int productSku;
    private int quantity;

}
