package com.buyzilla.services.ProductService.repository;


import com.buyzilla.services.ProductService.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuppliersRepository extends JpaRepository<Supplier,Integer> {
}
