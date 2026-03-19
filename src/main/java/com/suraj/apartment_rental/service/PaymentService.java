package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Payment;
import java.util.List;

public interface PaymentService extends BaseService<Payment, Long> {
    Payment save(Payment payment);
    List<Payment> findAll();
    List<Payment> findByLeaseId(Long leaseId);
    List<Payment> findByLeaseIdAndStatus(Long leaseId, Payment.PaymentStatus status);
    Payment markAsPaid(Long id);
    Payment markAsOverdue(Long id);
}
