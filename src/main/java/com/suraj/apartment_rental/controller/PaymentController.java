package com.suraj.apartment_rental.controller;

import com.suraj.apartment_rental.entity.Payment;
import com.suraj.apartment_rental.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController extends BaseController<Payment, Long> {

    public PaymentController(PaymentService service) {
        super(service);
    }

    @GetMapping("/lease/{leaseId}")
    public List<Payment> getPaymentsByLease(@PathVariable Long leaseId) {
        return ((PaymentService) service).findByLeaseId(leaseId);
    }

    @GetMapping("/lease/{leaseId}/status/{status}")
    public List<Payment> getPaymentsByLeaseAndStatus(
            @PathVariable Long leaseId,
            @PathVariable Payment.PaymentStatus status) {
        return ((PaymentService) service).findByLeaseIdAndStatus(leaseId, status);
    }

    @PostMapping("/{id}/pay")
    public Payment markPaid(@PathVariable Long id) {
        return ((PaymentService) service).markAsPaid(id);
    }

    @PostMapping("/{id}/overdue")
    public Payment markOverdue(@PathVariable Long id) {
        return ((PaymentService) service).markAsOverdue(id);
    }
}
