package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Tenant;

public interface TenantService extends BaseService<Tenant, Long> {
    Tenant findByIdReturnTenant(Long id);
}
