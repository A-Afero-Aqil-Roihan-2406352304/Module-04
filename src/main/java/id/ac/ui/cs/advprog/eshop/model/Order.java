package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Order {
    UUID id;
    List<Product> products;
    Long orderTime;
    String author;

    @Setter
    String status;

    public Order(UUID id, List<Product> products, Long orderTime, String author) {
    }

    public Order(UUID id, List<Product> products, Long orderTime, String author, String status) {
    }
}