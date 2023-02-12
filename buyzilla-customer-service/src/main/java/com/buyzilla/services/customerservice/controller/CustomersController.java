package com.buyzilla.services.customerservice.controller;

import com.buyzilla.services.customerservice.entity.Customer;
import com.buyzilla.services.customerservice.service.CustomerService;
import com.buyzilla.services.customerservice.util.ValidList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.buyzilla.services.customerservice.vo.CustomerVo;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customers")
public class CustomersController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping
    ResponseEntity<String> saveCustomer(@RequestBody @Valid ValidList<CustomerVo> customerVos){
        customerService.saveCustomer(customerVos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<String> updateCustomer(@RequestBody @Valid ValidList<CustomerVo> customerVos){
        customerService.updateCustomer(customerVos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cid}")
    ResponseEntity<String> deleteCustomer(@PathVariable Integer cid){
        customerService.deleteCustomer(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{cid}")
    ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerService.findByEmail(email),HttpStatus.OK);
    }


}
