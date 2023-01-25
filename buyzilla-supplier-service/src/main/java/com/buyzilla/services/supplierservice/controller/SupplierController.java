package com.buyzilla.services.supplierservice.controller;

import com.buyzilla.services.supplierservice.entity.Supplier;
import com.buyzilla.services.supplierservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping
    ResponseEntity<List<Supplier>> getSuppliers(){
        return supplierService.getSuppliers();
    }

    @GetMapping("/{sid}")
    ResponseEntity<Supplier> getSupplierById(@PathVariable Integer sid){
        return new ResponseEntity<>(supplierService.getSupplierById(sid), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<String> updateSupplier(@RequestBody Supplier supplier){
        supplierService.updateSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<String> addSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{sid}")
    ResponseEntity<String> deleteSupplier(@PathVariable Integer sid){
        supplierService.deleteSupplier(sid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
