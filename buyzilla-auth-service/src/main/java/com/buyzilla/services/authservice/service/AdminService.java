package com.buyzilla.services.authservice.service;

import com.buyzilla.services.authservice.entity.Admin;
import com.buyzilla.services.authservice.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
