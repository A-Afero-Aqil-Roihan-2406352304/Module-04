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

        if (paymentData != null && paymentData.containsKey("voucherCode")) {
            String voucherCode = paymentData.get("voucherCode");

            if (isValidVoucher(voucherCode)) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else {
            this.status = "REJECTED";
        }
    }

    private boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int numericalCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numericalCount++;
            }
        }

        return numericalCount == 8;
    }
}