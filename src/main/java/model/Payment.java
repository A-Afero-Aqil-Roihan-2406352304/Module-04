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

        if (paymentData == null) {
            this.status = "REJECTED";
            return;
        }

        if ("VOUCHER".equals(method)) {
            if (paymentData.containsKey("voucherCode") && isValidVoucher(paymentData.get("voucherCode"))) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else if ("BANK".equals(method)) {
            if (isValidBankTransfer(paymentData)) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else {
            this.status = "REJECTED";
        }
    }

    public Payment(UUID id, String method, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
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

    private boolean isValidBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.trim().isEmpty()) {
            return false;
        }

        if (referenceCode == null || referenceCode.trim().isEmpty()) {
            return false;
        }

        return true;
    }
}