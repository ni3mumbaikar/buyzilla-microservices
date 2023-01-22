package com.buyzilla.services.supplierservice.repository;


import com.buyzilla.services.supplierservice.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuppliersRepository extends JpaRepository<Supplier,Integer> {
}
