package com.example.manager.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull(message = "{catalogue.products.update.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.update.errors.title_is_invalid}")
        String title,

        @Size(min = 3, max = 300, message = "{catalogue.products.update.errors.details_are_invalid}")
        String details
) {}
