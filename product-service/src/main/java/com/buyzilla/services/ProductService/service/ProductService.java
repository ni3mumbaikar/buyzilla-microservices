package com.buyzilla.services.ProductService.service;


import com.buyzilla.services.ProductService.entity.Product;
import com.buyzilla.services.ProductService.exceptions.ProductAlreadyExistException;
import com.buyzilla.services.ProductService.exceptions.ProductNotFoundException;
import com.buyzilla.services.ProductService.exceptions.SupplierNotFoundException;
import com.buyzilla.services.ProductService.repository.ProductRepository;
import com.buyzilla.services.ProductService.repository.SuppliersRepository;
import com.buyzilla.services.ProductService.vo.ProductVo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;


@PropertySource("classpath:eng_exceptions.properties")
@Service //Create Injectable singleton object of service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SuppliersRepository suppliersRepository;
    @Autowired
    PlatformTransactionManager platformTransactionManager;
    TransactionTemplate transactionTemplate;
    @Autowired
    Environment environment;

    @PostConstruct
    void initTransactionManagement() {
        transactionTemplate = new TransactionTemplate(platformTransactionManager);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    //TODO : Study propagation
    public void saveProducts(List<ProductVo> productVos){
//        for (ProductVo product : productVos) {
//            if (productRepository.findById(product.getProductID()).isPresent()) {
//                throw new ProductAlreadyExistException(product.getProductID());
//            }
//            productRepository.save(getEntity(product));
//        }
        // enhanced control using programmatic transaction management.
        TransactionCallback<Void> transactionCallback = new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                for (ProductVo product : productVos) {
                    if (productRepository.findById(product.getProductID()).isPresent()) {
                        throw new ProductAlreadyExistException(environment.getProperty("product_already_exist"));
                    }
                    productRepository.save(getEntity(product));
                }
                return null;
            }

        };
        transactionTemplate.execute(transactionCallback);

    }

    public Product getProductByPid(Integer pid){
        return productRepository.findById(pid).orElseThrow(() -> new ProductNotFoundException(environment.getProperty("product_not_found")));
    }

    public void updateProducts(List<ProductVo> productVos) throws ProductNotFoundException, SupplierNotFoundException {
        for (ProductVo p : productVos) {
            if (productRepository.findById(p.getProductID()).isEmpty()) {
                throw new ProductNotFoundException(environment.getProperty("product_not_found"));
            }
            productRepository.save(getEntity(p));
        }
    }

    public Product getEntity(ProductVo productVo) throws SupplierNotFoundException {
        return Product.builder()
                .productName(productVo.getProductName())
                .productImage(productVo.getProductImage())
                .productID(productVo.getProductID())
                .price(productVo.getPrice())
                .supplier(suppliersRepository.findById(productVo.getSupplierID()).orElseThrow(() -> new SupplierNotFoundException(environment.getProperty("supplier_not_found"))))
                .unit(productVo.getUnit()).build();
    }

    public void deleteProductById(Integer pid) {
        productRepository.delete(productRepository.findById(pid).orElseThrow(() -> new ProductNotFoundException(environment.getProperty("product_not_found"))));
    }
}
