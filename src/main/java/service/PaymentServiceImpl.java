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
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());

        if (existingPayment != null) {
            Payment newPayment = new Payment(
                    existingPayment.getId(),
                    existingPayment.getMethod(),
                    existingPayment.getPaymentData(),
                    status
            );
            paymentRepository.save(newPayment);

            Order order = orderRepository.findById(existingPayment.getId());
            if (order != null) {
                if (status.equals("SUCCESS")) {
                    order.setStatus("SUCCESS");
                } else if (status.equals("REJECTED")) {
                    order.setStatus("FAILED");
                }
                orderRepository.save(order);
            }

            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment getPayment(UUID paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}