package com.suraj.apartment_rental.controller;

import com.suraj.apartment_rental.dto.CreateLeaseRequest;
import com.suraj.apartment_rental.entity.Lease;
import com.suraj.apartment_rental.service.LeaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leases")
@CrossOrigin(origins = "*")
public class LeaseController {

    @Autowired
    private LeaseService service;

    @GetMapping
    public List<Lease> getAllLeases() {
        return service.findAll();
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Lease> getTenantLeases(@PathVariable Long tenantId) {
        return service.findByTenantId(tenantId);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Lease createLease(@Valid @RequestBody CreateLeaseRequest request) {
        return service.createLease(
                request.getTenantId(),
                request.getApartmentId(),
                request.getStartDate(),
                request.getEndDate()
        );
    }

    @GetMapping("/{id}")
    public Lease getLease(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Lease updateLease(@PathVariable Long id, @Valid @RequestBody Lease lease) {
        return service.update(id, lease);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> endLease(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
