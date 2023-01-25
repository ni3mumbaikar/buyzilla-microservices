package com.buyzilla.services.supplierservice.service;

import com.buyzilla.services.supplierservice.entity.Supplier;
import com.buyzilla.services.supplierservice.exception.SupplierNotFoundException;
import com.buyzilla.services.supplierservice.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SuppliersRepository suppliersRepository;

    @Autowired
    Environment environment;

    public ResponseEntity<List<Supplier>> getSuppliers() {
        return new ResponseEntity<>(suppliersRepository.findAll(), HttpStatus.OK);
    }

    public void deleteSupplier(Integer sid) {
        System.out.println(suppliersRepository.findById(sid));
        suppliersRepository.delete(suppliersRepository.findById(sid).orElseThrow(() -> new SupplierNotFoundException(environment.getProperty("supplier_not_found"))));

    }

    public void updateSupplier(Supplier supplier) {
        System.out.println(supplier);
        if (suppliersRepository.findById(supplier.getSupplierID()).isPresent()) {
            suppliersRepository.save(supplier);
        } else {
            throw new SupplierNotFoundException(environment.getProperty("supplier_not_found"));
        }

    }

    public void addSupplier(Supplier shipper) {
        suppliersRepository.save(shipper);
    }

    public Supplier getSupplierById(Integer sid) {
        return suppliersRepository.findById(sid).orElseThrow(()-> new SupplierNotFoundException(environment.getProperty("supplier_not_found")));
    }
}