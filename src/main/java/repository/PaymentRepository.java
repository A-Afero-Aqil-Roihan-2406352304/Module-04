package repository;

import model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        return null;
    }

    public Payment findById(UUID id) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return null;
    }
}