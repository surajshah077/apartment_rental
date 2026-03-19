package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.*;
import com.suraj.apartment_rental.exception.BusinessException;
import com.suraj.apartment_rental.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaseServiceImpl implements LeaseService {

    @Autowired private LeaseRepository repository;
    @Autowired private ApartmentService apartmentService;
    @Autowired private TenantService tenantService;

    @Override
    public Lease save(Lease lease) { return repository.save(lease); }

    @Override
    public List<Lease> findAll() { return repository.findAll(); }

    @Override
    public Lease findById(Long id) {
        Optional<Lease> lease = repository.findById(id);
        return lease.orElseThrow(() -> new RuntimeException("Lease not found: " + id));
    }

    @Override
    public List<Lease> findByTenantId(Long tenantId) {
        return repository.findByTenantId(tenantId);
    }

    @Override
    public Lease createLease(Long tenantId, Long apartmentId, Date startDate, Date endDate) {
        Tenant tenant = tenantService.findByIdReturnTenant(tenantId);  // ✅ Uses your method
        Apartment apartment = apartmentService.findById(apartmentId);

        if (!apartment.getAvailable()) {
            throw new IllegalStateException("Apartment not available ID: " + apartmentId);
        }
        if (tenant.getIncome() < apartment.getRentAmount() * 3) {
            throw new IllegalStateException("Income too low (3x rent required)");
        }
        if (startDate.after(endDate)) {
            throw new IllegalStateException("Invalid dates");
        }
        if (!apartment.getAvailable()) {
            throw new BusinessException("Apartment not available ID: " + apartmentId);
        }

        Lease lease = new Lease();
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);
        lease.setTenant(tenant);
        lease.setApartment(apartment);

        apartment.setAvailable(false);
        apartmentService.save(apartment);
        return repository.save(lease);
    }

    @Override
    public Lease update(Long id, Lease leaseDetails) {
        Lease lease = findById(id);
        lease.setStartDate(leaseDetails.getStartDate());
        lease.setEndDate(leaseDetails.getEndDate());
        return repository.save(lease);
    }

    @Override
    public void delete(Long id) {
        Lease lease = findById(id);
        lease.getApartment().setAvailable(true);
        apartmentService.save(lease.getApartment());
        repository.deleteById(id);
    }
}
