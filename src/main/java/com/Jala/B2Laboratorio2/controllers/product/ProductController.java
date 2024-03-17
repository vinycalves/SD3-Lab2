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
    public Product getProductById(@PathVariable("productID") UUID productID) {
        return productService.productById(productID);
    }

    @GetMapping(path = "/name/{Name}")
    public List<Product> getProductByName(@PathVariable("Name") String name){
        return productService.productFindByName(name);
    }


    @GetMapping("/price/{price}")
    public List<Product> getProductByPrice(@PathVariable("price") BigDecimal price) {
        return productService.productFindByPrice(price);
    }

    @GetMapping("/description/{description}")
    public List<Product> getProductByDescription(@PathVariable("description") String description) {
        return productService.productFindByDescription(description);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Product> getProductByQuantity(@PathVariable("quantity") BigDecimal quantity) {
        return productService.productFindByQuantity(quantity);
    }

    @DeleteMapping(path = "{productID}")
    public void deleteProduct(@PathVariable("productID") UUID uuid) {
        productService.deleteProduct(uuid);
    }

    @PutMapping(path = "{productID}")
    public void updateProduct(
            @PathVariable("productID") UUID productUUID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) BigDecimal quantity
    ){
        productService.updateStudent(productUUID, name, description, price, quantity);
    }
}
