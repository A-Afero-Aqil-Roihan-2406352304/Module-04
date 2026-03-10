package model;

import lombok.Getter;
import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    private UUID id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(UUID id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        // Skeleton: status di-hardcode sementara
        this.status = "SUCCESS";
    }
}