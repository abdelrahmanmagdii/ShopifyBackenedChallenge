package com.shopify.challenge.amer.logistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shopify.challenge.amer.logistics.util.ProductSerializer;
import com.shopify.challenge.amer.logistics.util.WarehouseSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductWarehouseKey implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonSerialize(using = ProductSerializer.class)
    @JsonProperty("product_sku")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonSerialize(using = WarehouseSerializer.class)
    @JsonProperty("warehouse_id")
    private Warehouse warehouse;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWarehouseKey that = (ProductWarehouseKey) o;
        return Objects.equals(product, that.product) && Objects.equals(warehouse, that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, warehouse);
    }

    @Override
    public String toString() {
        return "ProductWarehouseKey{" +
                "product=" + ((product==null)?"null":product.getSKU()) +
                ", warehouse=" + ((warehouse==null)?"null":warehouse.getId()) +
                '}';
    }
}
