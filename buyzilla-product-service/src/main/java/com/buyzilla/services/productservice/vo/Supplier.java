package com.buyzilla.services.productservice.vo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer supplierID;

    private Integer postalCode;
    private String supplierName, Address, City;
}
