package com.buyzilla.services.productservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products_10708461")
public class Product {

    @Column(name = "supplierID")
    Integer supplierID;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer productID;

    private Integer unit, price;
    private String productName;
    @Column(name = "product_image")
    private String productImage;

}
