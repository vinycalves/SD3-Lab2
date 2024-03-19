package com.Jala.B2Laboratorio2.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDto(@NotNull String name, @NotNull String description, @Positive BigDecimal price, @Positive BigDecimal quantity) {
}
