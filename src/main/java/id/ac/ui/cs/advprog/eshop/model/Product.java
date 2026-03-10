package id.ac.ui.cs.advprog.eshop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Product {
    private UUID productId = UUID.randomUUID();

    @NotNull(message = "Name cannot be empty")
    private String productName;

    @Min(value = 1, message = "Quantity must be a positive integer")
    private int productQuantity;
}

