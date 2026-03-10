package service;

import model.Order;
import model.Payment;
import repository.PaymentRepository;
import repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }

    @Override
    public Payment getPayment(UUID paymentId) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }
}