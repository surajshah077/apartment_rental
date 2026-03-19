package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Payment;
import com.suraj.apartment_rental.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired private PaymentRepository repository;
    @Autowired private LeaseService leaseService;

    @Override
    public Payment save(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (payment.getStatus() == null) {
            payment.setStatus(Payment.PaymentStatus.PENDING);
        }
        return repository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public Payment update(Long id, Payment entity) {
        Payment payment = findById(id);
        payment.setAmount(entity.getAmount());
        payment.setPaymentDate(entity.getPaymentDate());
        // update other fields as needed
        return save(payment);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Payment> findByLeaseId(Long leaseId) {
        return repository.findByLeaseIdAndStatus(leaseId, Payment.PaymentStatus.PAID);
    }

    @Override
    public List<Payment> findByLeaseIdAndStatus(Long leaseId, Payment.PaymentStatus status) {
        return repository.findByLeaseIdAndStatus(leaseId, status);
    }

    @Override
    public Payment markAsPaid(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(Payment.PaymentStatus.PAID);
        return repository.save(payment);
    }

    @Override
    public Payment markAsOverdue(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(Payment.PaymentStatus.OVERDUE);
        return repository.save(payment);
    }

     @Scheduled(cron = "0 0 0 * * ?")  // Midnight daily
    public void checkOverduePayments() {
         }
}
