package com.Jala.B2Laboratorio2.repositories.product;

import com.Jala.B2Laboratorio2.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
