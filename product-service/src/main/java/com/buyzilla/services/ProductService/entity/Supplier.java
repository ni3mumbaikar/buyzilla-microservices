package com.buyzilla.services.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers_10708461")
public class Supplier {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer supplierID;

    private Integer postalCode;
    private String supplierName, Address, City;
}
