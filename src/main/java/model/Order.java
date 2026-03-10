package model;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Product;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Order {
    private UUID id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    private String status;

    public Order(UUID id, List<Product> products, Long orderTime, String author) {
        this.id = id;
        this.orderTime = orderTime;
        this.author = author;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public Order(UUID id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}