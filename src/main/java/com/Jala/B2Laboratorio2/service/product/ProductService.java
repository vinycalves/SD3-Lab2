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
        Product product = new Product(productDto.name(), productDto.description(), productDto.price(), productDto.quantity());
        return productRepository.save(product);
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public Product productById(UUID productUUID) {
        return productRepository.findById(productUUID)
                .orElseThrow(() -> new UUIDException(productUUID));
    }

    public List<Product> productByAttributes(String name, String description, BigDecimal price, BigDecimal quantity) {
        return productRepository.findByAttributes(name, description, price, quantity);
    }

    public void deleteProduct(UUID productUUID) {
        if (!productRepository.existsById(productUUID)) {
            throw new UUIDException(productUUID);
        }
        productRepository.deleteById(productUUID);
    }

    @Transactional
    public void updateProduct(UUID productUUID, ProductDto productDto) {
        Product product = productRepository.findById(productUUID)
                .orElseThrow(() -> new UUIDException(productUUID));

        if (!Objects.equals(product.getName(), productDto.name())) {
            product.setName(productDto.name());
        }
        if (!Objects.equals(product.getDescription(), productDto.description())) {
            product.setDescription(productDto.description());
        }
        if (!Objects.equals(product.getPrice(), productDto.price())) {
            product.setPrice(productDto.price());
        }
        if (!Objects.equals(product.getQuantity(), productDto.quantity())) {
            product.setQuantity(productDto.quantity());
        }
    }
}
