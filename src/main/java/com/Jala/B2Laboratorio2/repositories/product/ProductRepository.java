package com.Jala.B2Laboratorio2.repositories.product;

import com.Jala.B2Laboratorio2.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR p.name = :name) AND " +
            "(:description IS NULL OR p.description = :description) AND " +
            "(:price IS NULL OR p.price = :price) AND " +
            "(:quantity IS NULL OR p.quantity = :quantity)")
    List<Product> findByAttributes(@Param("name") String name, @Param("description") String description,
                                   @Param("price") BigDecimal price, @Param("quantity") BigDecimal quantity);

}
