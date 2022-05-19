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
public class Warehouse {
    @Id
    @Min(value = 1)
    @Max(value = 100000)
    Integer id;

    @Column
    @NotBlank
    @Pattern(regexp = "^[A-Za-z 0-9]{3,50}$", message = "Only characters, digits and space are allowed from 3 to 50")
    String name;

    @Column
    @NotBlank
    @Pattern(regexp = "^[A-Za-z 0-9]{3,50}$", message = "Only characters, digits and space are allowed from 3 to 50")
    String country;

    @Column
    @NotBlank
    @Pattern(regexp = "^[A-Za-z 0-9]{3,50}$", message = "Only characters, digits and space are allowed from 3 to 50")
    String state;

    @Column
    @NotBlank
    String zipcode;

@OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "key.warehouse",
        cascade = CascadeType.ALL)
    private List<ProductWarehouse> tags = new ArrayList<>();

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }


}
