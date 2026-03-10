package service;

import model.Order;
import repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) { return null; }

    @Override
    public Order updateStatus(UUID orderId, String status) { return null; }

    @Override
    public List<Order> findAllByAuthor(String author) { return null; }

    @Override
    public Order findById(UUID orderId) { return null; }
}