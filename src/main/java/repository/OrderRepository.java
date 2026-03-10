package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepository {
    private List<Order> orderData = new ArrayList<Order>();
    public Order save (Order order){return null;}

    public Order findById(UUID id){return null;}

    public List<Order> findAllByAuthor(String author){return null;}
}