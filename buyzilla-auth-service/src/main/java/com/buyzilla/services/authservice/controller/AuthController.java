package com.buyzilla.services.authservice.controller;

import com.buyzilla.services.authservice.entity.Admin;
import com.buyzilla.services.authservice.entity.Customer;
import com.buyzilla.services.authservice.exceptions.AuthFailedException;
import com.buyzilla.services.authservice.service.AdminService;
import com.buyzilla.services.authservice.vo.AdminVo;
import com.buyzilla.services.authservice.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    AdminService adminService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Environment environment;

    @PostMapping("/api/v1/auth/user")
    ResponseEntity<Customer> validateUser(@RequestBody CustomerVo customerVo){
        Customer customer = restTemplate.getForObject("http://buyzilla-customer-service/api/v1/customers/"+customerVo.getEmail(),Customer.class);
        if(customer == null){
            throw new AuthFailedException(environment.getProperty("authentication_failed"));
        }
        else if(customer.getEmail().equals(customerVo.getEmail()) && customer.getPassword().equals(customerVo.getPassword())){
            return ResponseEntity.ok(customer);
        }
        throw new AuthFailedException(environment.getProperty("authentication_failed"));

    }

    @PostMapping("/api/v1/auth/admin")
    ResponseEntity<Admin> validateAdmin(@RequestBody AdminVo admin){
        Admin admin1 = adminService.findByEmail(admin.getEmail());
        if(admin1 == null){
            throw new AuthFailedException(environment.getProperty("authentication_failed"));
        }
        if(admin1.getEmail().equals(admin.getEmail()) && admin1.getPassword().equals(admin.getPassword())){
            return ResponseEntity.ok(admin1);
        } else {
            throw new AuthFailedException(environment.getProperty("authentication_failed"));
        }
    }

}
