package com.shopify.challenge.amer.logistics.dto;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table(name = "product_warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class ProductWarehouse {
//mark id as primary key
    @EmbeddedId
    ProductWarehouseKey key;
    @Column(name="product_quantity")
    Integer productQuantity;



}
