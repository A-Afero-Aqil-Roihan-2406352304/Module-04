package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }
    public Product findbyId(UUID id){
        for (Product product: productData) {
          if (product.getProductId().equals(id)){
              return product;
          }
        }
        return null;
    }

    public Product edit(Product productBaru){
        for (int i=0; i<productData.size();i++){
            Product productLama = productData.get(i);
            if (productBaru.getProductId().equals(productLama.getProductId())){
                productData.set(i,productBaru);
                return productBaru;
            }
        }
        return null;
    }

    public void delete(UUID id){
        productData.removeIf(product -> product.getProductId().equals(id));
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}