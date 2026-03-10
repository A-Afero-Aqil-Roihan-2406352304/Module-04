package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId (UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getProductName(), createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Product product2 = new Product();
        product2.setProductId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size()); // Memastikan size list sesuai
        assertEquals("Sampo Cap Bambang", result.get(0).getProductName());
        assertEquals("Sampo Cap Usep", result.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        when(productRepository.findbyId(id)).thenReturn(product);
        Product foundProduct = productService.findbyId(id);
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        verify(productRepository, times(1)).findbyId(id);
    }

    @Test
    void testEdit() {
        when(productRepository.edit(product)).thenReturn(product);
        Product editedProduct = productService.edit(product);
        assertNotNull(editedProduct);
        assertEquals("Sampo Cap Bambang", editedProduct.getProductName());
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDelete() {
        UUID id = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.delete(id);
        verify(productRepository, times(1)).delete(id); // delete di repo terpanggil
    }
}