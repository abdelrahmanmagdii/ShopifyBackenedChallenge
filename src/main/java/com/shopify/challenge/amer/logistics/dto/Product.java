package com.shopify.challenge.amer.logistics.dto;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

//mark class as an Entity
@Entity
//defining class name as Table name
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

//mark id as primary key
    @Id
    @Column(name="sku")
    @Min(value = 1)
    @Max(value = 100000)
    Integer SKU;
    @Column
    @NotBlank
    @Pattern(regexp = "^[A-Za-z 0-9]{3,50}$", message = "Only characters, digits and space are allowed from 3 to 50")
    String name;
    @Column
    @NotBlank
    String description;
    @Column
    @NotBlank
    String category;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "key.product",
            cascade = CascadeType.ALL)
    private List<ProductWarehouse> tags = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "SKU=" + SKU +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }



}
