package com.buyzilla.services.customerservice.service;

import com.buyzilla.services.customerservice.entity.Customer;
import com.buyzilla.services.customerservice.excception.CustomerAlreadyExistException;
import com.buyzilla.services.customerservice.excception.UserNotFoundException;
import com.buyzilla.services.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.buyzilla.services.customerservice.vo.CustomerVo;

import java.util.List;

@PropertySource("classpath:eng_exceptions.properties")
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Environment environment;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(List<CustomerVo> customerVos){
        for (CustomerVo customerVo : customerVos) {
            if (customerRepository.findById(customerVo.getCustomerID()).isPresent()) {
                throw new CustomerAlreadyExistException(environment.getProperty("customer_already_exist"));
            }
            customerRepository.save(getEntity(customerVo));
        }
    }

    public void updateCustomer(List<CustomerVo> customerVos){
        for (CustomerVo customerVo : customerVos) {
            if (customerRepository.findById(customerVo.getCustomerID()).isEmpty()) {
                throw new UserNotFoundException(environment.getProperty("customer_not_found"));
            }
            customerRepository.save(getEntity(customerVo));
        }
    }

    public Customer getEntity(CustomerVo customerVo) {
        return Customer.builder()
                .customerName(customerVo.getCustomerName())
                .address(customerVo.getAddress())
                .postalCode(customerVo.getPostalCode())
                .city(customerVo.getCity())
                .email(customerVo.getEmail())
                .password(customerVo.getPassword())
                .country(customerVo.getCountry()).build();
    }

    public void deleteCustomer(Integer cid) {
        customerRepository.delete(customerRepository.findById(cid).orElseThrow(()-> new UserNotFoundException(environment.getProperty("customer_not_found"))));
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
