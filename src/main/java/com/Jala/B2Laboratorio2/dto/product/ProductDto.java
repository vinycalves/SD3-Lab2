package com.Jala.B2Laboratorio2.dto.product;

import java.math.BigDecimal;

public record ProductDto(String name, String description, BigDecimal price, BigDecimal quantity) {
}
