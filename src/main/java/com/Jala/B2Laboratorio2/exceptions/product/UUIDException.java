package com.Jala.B2Laboratorio2.exceptions.product;

import java.util.UUID;

public class UUIDException extends IllegalStateException {
    public UUIDException(UUID uuid) {
        super("Product with ID " + uuid + " doesn't exists");
    }
}
