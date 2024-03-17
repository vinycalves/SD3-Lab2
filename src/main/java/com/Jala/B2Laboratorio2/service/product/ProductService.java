package com.Jala.B2Laboratorio2.service.product;

import com.Jala.B2Laboratorio2.dto.product.ProductDto;
import com.Jala.B2Laboratorio2.entities.product.Product;
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

    public void deleteProduct(UUID productUUID) {
        boolean exists = productRepository.existsById(productUUID);
        if (!exists) {
            throw new IllegalStateException("Product with ID " + productUUID + " doesn't exists");
        }
        productRepository.deleteById(productUUID);
    }

    @Transactional
    public void updateStudent(UUID productUUID, String name, String description, BigDecimal price, BigDecimal quantity) {
        var product = productRepository.findById(productUUID)
                .orElseThrow(() -> new IllegalStateException("Product with id " + productUUID + " doesn't exists"));

        if (!name.isBlank() && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }
        if (!description.isBlank() && !Objects.equals(product.getDescription(), description)) {
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
