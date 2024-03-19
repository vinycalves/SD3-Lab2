package com.Jala.B2Laboratorio2.controllers.product;

import com.Jala.B2Laboratorio2.dto.product.ProductDto;
import com.Jala.B2Laboratorio2.entities.product.Product;
import com.Jala.B2Laboratorio2.service.product.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Product> postProducts(@Valid @RequestBody ProductDto productDto) {
        Product createdProduct = productService.createNewProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.productList();
    }

    @GetMapping(path = "{productID}")
    public Product getProductById(@PathVariable("productID") UUID productID) {
        return productService.productById(productID);
    }

    @GetMapping("/search")
    public List<Product> getProductByAttributes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) BigDecimal quantity) {
        return productService.productByAttributes(name, description, price, quantity);
    }

    @DeleteMapping(path = "{productID}")
    public void deleteProduct(@PathVariable("productID") UUID uuid) {
        productService.deleteProduct(uuid);
    }

    @PutMapping(path = "{productID}")
    public void updateProduct(@PathVariable("productID") UUID productUUID, @Valid @RequestBody ProductDto productDto) {
        productService.updateProduct(productUUID, productDto);
    }
}
