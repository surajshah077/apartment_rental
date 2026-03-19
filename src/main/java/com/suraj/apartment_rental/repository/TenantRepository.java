package com.suraj.apartment_rental.repository;

import com.suraj.apartment_rental.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByNameContainingIgnoreCase(String name);
    List<Tenant> findByIncomeGreaterThan(Double minIncome);
}
