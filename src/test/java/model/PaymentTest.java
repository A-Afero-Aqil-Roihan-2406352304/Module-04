package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentValidVoucher() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        UUID id = UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment(id, "VOUCHER", paymentData);

        assertEquals(id, payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherLength() {
        paymentData.put("voucherCode", "ESHOP1234ABC567"); // Hanya 15 karakter
        UUID id = UUID.fromString("7f9e15bb-4b15-42f4-aebc-c3af385fb078");
        Payment payment = new Payment(id, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherStartString() {
        paymentData.put("voucherCode", "OSHEP1234ABC5678"); // Tidak diawali "ESHOP"
        UUID id = UUID.fromString("e334ef40-9eff-4da8-9487-8ee697ecbf1e");
        Payment payment = new Payment(id, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherNumericalCharacters() {
        paymentData.put("voucherCode", "ESHOP123ABCDEFGH"); // Hanya 3 angka, butuh 8
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyVoucherCode() {
        paymentData.put("voucherCode", "");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentValidBankTransfer() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF12345678");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "BANK", paymentData);

        assertEquals("BANK", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidBankTransferEmptyBankName() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "REF12345678");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidBankTransferNullBankName() {
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", "REF12345678");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidBankTransferEmptyReferenceCode() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidBankTransferNullReferenceCode() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", null);
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id, "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}