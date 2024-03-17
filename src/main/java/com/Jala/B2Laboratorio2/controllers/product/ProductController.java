package com.Jala.B2Laboratorio2.controllers.product;

import com.Jala.B2Laboratorio2.entities.product.Product;
import com.Jala.B2Laboratorio2.repositories.product.ProductRepository;
import com.Jala.B2Laboratorio2.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> postProducts(@RequestBody Product product) {
        Product prd = Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(prd));
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.productList();
    }


}
