package com.Jala.B2Laboratorio2.service.product;

import com.Jala.B2Laboratorio2.entities.product.Product;
import com.Jala.B2Laboratorio2.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createNewProduct() {

    }

    public List<Product> productList(){
        return productRepository.findAll();
    }
}
