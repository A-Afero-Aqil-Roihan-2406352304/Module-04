package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Product create(Product product);
    public Product findbyId(UUID id);
    public Product edit(Product product);
    public void delete(UUID id);
    public List<Product> findAll();
}