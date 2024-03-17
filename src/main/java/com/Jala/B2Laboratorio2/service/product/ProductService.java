package com.Jala.B2Laboratorio2.service.product;

import com.Jala.B2Laboratorio2.dto.product.ProductDto;
import com.Jala.B2Laboratorio2.entities.product.Product;
import com.Jala.B2Laboratorio2.exceptions.product.UUIDException;
import com.Jala.B2Laboratorio2.repositories.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createNewProduct(ProductDto productDto) {
        var prd = Product.builder()
                .name(productDto.name())
                .price(productDto.price())
                .description(productDto.description())
                .quantity(productDto.quantity())
                .build();
        return productRepository.save(prd);
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public Product productById(UUID productUUID) {
        var exists = productRepository.findById(productUUID).isPresent();
        if (!exists) {
            throw new UUIDException(productUUID);
        }
        return productRepository.findById(productUUID).get();
    }
    
    
    public List<Product> productFindByName(String name) {
        return productRepository.findAll().stream().filter(product -> product.getName().equals(name)).toList();
    }

    public List<Product> productFindByPrice(BigDecimal price) {
        return productRepository.findAll().stream().filter(product -> product.getPrice().equals(price)).toList();
    }

    public List<Product> productFindByDescription(String description) {
        return productRepository.findAll().stream().filter(product -> product.getDescription().equals(description)).toList();
    }

    public List<Product> productFindByQuantity(BigDecimal quantity) {
        return productRepository.findAll().stream().filter(product -> product.getQuantity().equals(quantity)).toList();
    }

    public void deleteProduct(UUID productUUID) {
        boolean exists = productRepository.existsById(productUUID);
        if (!exists) {
            throw new UUIDException(productUUID);
        }
        productRepository.deleteById(productUUID);
    }

    @Transactional
    public void updateStudent(UUID productUUID, String name, String description, BigDecimal price, BigDecimal quantity) {
        var product = productRepository.findById(productUUID)
                .orElseThrow(() -> new UUIDException(productUUID));

        if (name != null && !name.isBlank() && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }
        if (description != null && !description.isBlank() && !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }
        if (price != null && !Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }
        if (quantity != null && !Objects.equals(product.getQuantity(), quantity)) {
            product.setQuantity(quantity);
        }
    }
}
