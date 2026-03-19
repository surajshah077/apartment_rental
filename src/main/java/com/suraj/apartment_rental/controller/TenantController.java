package com.suraj.apartment_rental.controller;

import com.suraj.apartment_rental.entity.Tenant;
import com.suraj.apartment_rental.service.TenantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenants")
public class TenantController extends BaseController<Tenant, Long> {

    public TenantController(TenantService service) {
        super(service);
    }
}
