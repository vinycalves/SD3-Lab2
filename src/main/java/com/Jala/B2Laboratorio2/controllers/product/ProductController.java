package com.Jala.B2Laboratorio2.controllers.product;

import com.Jala.B2Laboratorio2.dto.product.ProductDto;
import com.Jala.B2Laboratorio2.entities.product.Product;
import com.Jala.B2Laboratorio2.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> postProducts(@RequestBody ProductDto productDto) {
        var prd = productService.createNewProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prd);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.productList();
    }

    @GetMapping(path = "{productID}")
    public Product getProductById(
            @PathVariable("productID") UUID productID,
            @RequestParam(required = false) String name) {
        return productService.productById(productID);
    }

    @DeleteMapping(path = "{productID}")
    public void deleteProduct(@PathVariable("productID") UUID uuid) {
        productService.deleteProduct(uuid);
    }

    @PutMapping(path = "{productID}")
    public void updateProduct(
            @PathVariable("productID") UUID productUUID,
            @RequestBody ProductDto productDto
    ) {
        productService.updateStudent(productUUID, productDto);
    }
}
