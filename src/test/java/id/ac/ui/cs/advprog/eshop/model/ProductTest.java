package id.ac.ui.cs.advprog.eshop.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    private Validator validator;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void testGetProductId() {
        assertEquals(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"), this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testProductQuantityPositive() {
        this.product.setProductQuantity(10);

        Set<ConstraintViolation<Product>> violations = validator.validate(this.product);
        assertTrue(violations.isEmpty()); //validasi tidak ada error
    }

    @Test
    void testProductQuantityNegative() {
        this.product.setProductQuantity(-1);

        Set<ConstraintViolation<Product>> violations = validator.validate(this.product);

        assertFalse(violations.isEmpty()); //validasi ada error
        assertEquals(1, violations.size());
        assertEquals("Quantity must be a positive integer", violations.iterator().next().getMessage());
    }

}