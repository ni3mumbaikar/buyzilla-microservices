package com.buyzilla.services.productservice.controller;


import com.buyzilla.services.productservice.entity.Product;
import com.buyzilla.services.productservice.service.ProductService;
import com.buyzilla.services.productservice.util.ValidList;
import com.buyzilla.services.productservice.vo.ProductVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired //inject existing object from registry
    ProductService productService;

    @GetMapping
    ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts()) ;
    }

    @GetMapping("/{pid}")
    ResponseEntity<Product> getProductByPid(@PathVariable Integer pid) {
        return ResponseEntity.ok(productService.getProductByPid(pid));
    }

    @PostMapping
    ResponseEntity<String> saveProducts(@RequestBody @Valid ValidList<ProductVo> productVos) {
        productVos.forEach(System.out::println);
        productService.saveProducts(productVos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<String> updateProducts(@RequestBody @Valid ValidList<ProductVo> productVos) {
        productService.updateProducts(productVos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pid}")
    ResponseEntity<HttpStatus> deleteProduct(@PathVariable Integer pid){
        productService.deleteProductById(pid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



