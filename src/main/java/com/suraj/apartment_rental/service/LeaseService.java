package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Lease;
import java.util.Date;
import java.util.List;

public interface LeaseService {
    Lease save(Lease lease);
    List<Lease> findAll();
    Lease findById(Long id);
    List<Lease> findByTenantId(Long tenantId);
    Lease createLease(Long tenantId, Long apartmentId, Date startDate, Date endDate);
    Lease update(Long id, Lease lease);
    void delete(Long id);
}
