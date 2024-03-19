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

    public void deleteProduct(UUID productUUID) {
        boolean exists = productRepository.existsById(productUUID);
        if (!exists) {
            throw new UUIDException(productUUID);
        }
        productRepository.deleteById(productUUID);
    }

    @Transactional
    public void updateStudent(UUID productUUID, ProductDto productDto) {
        var product = productRepository.findById(productUUID)
                .orElseThrow(() -> new UUIDException(productUUID));

        if (productDto.name() != null && !productDto.name().isBlank() && !Objects.equals(product.getName(), productDto.name())) {
            product.setName(productDto.name());
        }
        if (productDto.description() != null && !productDto.description().isBlank() && !Objects.equals(product.getDescription(), productDto.description())) {
            product.setDescription(productDto.description());
        }
        if (productDto.price() != null && !Objects.equals(product.getPrice(), productDto.price())) {
            product.setPrice(productDto.price());
        }
        if (productDto.quantity() != null && !Objects.equals(product.getQuantity(), productDto.quantity())) {
            product.setQuantity(productDto.quantity());
        }
    }
}
