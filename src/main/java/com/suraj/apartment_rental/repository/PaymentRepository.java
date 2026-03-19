package com.suraj.apartment_rental.repository;

import com.suraj.apartment_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByLeaseIdAndStatus(Long leaseId, Payment.PaymentStatus status);
    List<Payment> findByLeaseId(Long leaseId);
}
