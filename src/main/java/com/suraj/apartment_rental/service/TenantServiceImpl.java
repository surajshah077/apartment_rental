package com.suraj.apartment_rental.service;


import com.suraj.apartment_rental.entity.Tenant;
import com.suraj.apartment_rental.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository repo;

    @Override
    public Tenant save(Tenant tenant) {
        if (tenant.getIncome() < 1000) {
            throw new IllegalArgumentException("Income too low (minimum 1000€)");
        }
        return repo.save(tenant);
    }
    @Override
    public List<Tenant> findAll() {
        return repo.findAll();
    }

    @Override
    public Tenant findById(Long id) {
        return findByIdReturnTenant(id);
    }
    @Override
    public Tenant findByIdReturnTenant(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found: " + id));
    }

    @Override
    public Tenant update(Long id, Tenant tenantDetails) {
        Tenant tenant = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found: " + id));
        tenant.setName(tenantDetails.getName());
        tenant.setEmail(tenantDetails.getEmail());
        tenant.setIncome(tenantDetails.getIncome());
        return repo.save(tenant);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
