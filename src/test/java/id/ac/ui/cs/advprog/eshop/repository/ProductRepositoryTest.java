package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

        //set null product
        Product producttanpaId= new Product();
        producttanpaId.setProductId(null);
        Product productBaru = productRepository.create(producttanpaId);
        assertNotNull(productBaru);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }


    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        product.setProductName("Sampo1");
        product.setProductQuantity(10);
        productRepository.create(product);

        //Objek baru dengan ID sama tapi data beda
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Sampo2");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.edit(updatedProduct);

        //compare
        assertNotNull(result);
        assertEquals("Sampo2", result.getProductName());
        assertEquals(20, result.getProductQuantity());

        Product checkInRepo = productRepository.findbyId(product.getProductId());
        assertEquals("Sampo2", checkInRepo.getProductName());
    }

    @Test
    void testEditProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId(UUID.randomUUID()); // ID random
        nonExistentProduct.setProductName("Produk");
        nonExistentProduct.setProductQuantity(0);

        Product result = productRepository.edit(nonExistentProduct);

        //Cek apakah result berisi product
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        product.setProductName("Sampo");
        product.setProductQuantity(5);
        productRepository.create(product);

        // cek Produk Ada
        assertNotNull(productRepository.findbyId(product.getProductId()));

        productRepository.delete(product.getProductId());

        // cek apakah kosong
        Product result = productRepository.findbyId(product.getProductId());
        assertNull(result);

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        productRepository.create(product);

        UUID randomId = UUID.randomUUID();
        productRepository.delete(randomId);

        // cek apakah produk masih ada jika id tidak ditemukan untuk didelete
        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
    }
}