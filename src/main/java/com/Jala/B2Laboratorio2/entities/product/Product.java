package com.Jala.B2Laboratorio2.entities.product;

import com.Jala.B2Laboratorio2.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "TB_PRODUCTS")
public class Product extends AbstractEntity {
    @Column(name = "NM_PRODUCT")
    private String name;
    @Column(name = "DS_PRODUCT")
    private String description;
    @Column(name = "VL_PRICE", precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "NM_QUANTITY", precision = 10, scale = 2)
    private BigDecimal quantity;

    public Product() {

    }
}

