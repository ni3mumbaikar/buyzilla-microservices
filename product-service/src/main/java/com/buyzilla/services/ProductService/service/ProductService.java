package com.buyzilla.services.ProductService.service;


import com.buyzilla.services.ProductService.entity.Product;
import com.buyzilla.services.ProductService.entity.Supplier;
import com.buyzilla.services.ProductService.exception.ProductAlreadyExistException;
import com.buyzilla.services.ProductService.exception.ProductNotFoundException;
import com.buyzilla.services.ProductService.repository.ProductRepository;
import com.buyzilla.services.ProductService.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@PropertySource("classpath:eng_exceptions.properties")
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Environment environment;
    @Autowired
    RestTemplate restTemplate;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveProducts(List<ProductVo> productVos) {
        for (ProductVo product : productVos) {
            if (productRepository.findById(product.getProductID()).isPresent()) {
                throw new ProductAlreadyExistException(environment.getProperty("product_already_exist"));
            }
            productRepository.save(getEntity(product));
        }
    }

    public Product getProductByPid(Integer pid) {
        return productRepository.findById(pid).orElseThrow(() -> new ProductNotFoundException(environment.getProperty("product_not_found")));
    }

    public void updateProducts(List<ProductVo> productVos) {
        for (ProductVo p : productVos) {
            if (productRepository.findById(p.getProductID()).isEmpty()) {
                throw new ProductNotFoundException(environment.getProperty("product_not_found"));
            }
            productRepository.save(getEntity(p));
        }
    }

    public Product getEntity(ProductVo productVo) {
        return Product.builder()
                .productName(productVo.getProductName())
                .productImage(productVo.getProductImage())
                .productID(productVo.getProductID())
                .price(productVo.getPrice())
                .supplier(restTemplate.getForObject("http://supplier-service/api/v1/suppliers", Supplier.class))
                .unit(productVo.getUnit()).build();
    }

    public void deleteProductById(Integer pid) {
        productRepository.delete(productRepository.findById(pid).orElseThrow(() -> new ProductNotFoundException(environment.getProperty("product_not_found"))));
    }
}
