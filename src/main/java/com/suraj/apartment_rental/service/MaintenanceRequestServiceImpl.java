package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.MaintenanceRequest;
import com.suraj.apartment_rental.entity.MaintenanceRequest.RequestStatus;
import com.suraj.apartment_rental.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    @Autowired private MaintenanceRequestRepository repository;

    @Override
    public MaintenanceRequest save(MaintenanceRequest request) {
        if (request.getDescription() == null || request.getDescription().length() < 10) {
            throw new IllegalArgumentException("Description must be at least 10 characters");
        }
        if (request.getStatus() == null) {
            request.setStatus(RequestStatus.PENDING);
        }
        return repository.save(request);
    }

    @Override
    public List<MaintenanceRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public MaintenanceRequest findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public MaintenanceRequest update(Long id, MaintenanceRequest entity) {
        MaintenanceRequest request = findById(id);
        request.setDescription(entity.getDescription());
        // update other fields
        return save(request);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MaintenanceRequest> findByLeaseId(Long leaseId) {
        return repository.findByLeaseId(leaseId);  // Add to repo
    }

    @Override
    public List<MaintenanceRequest> findByLeaseIdAndStatus(Long leaseId, RequestStatus status) {
        return repository.findByLeaseIdAndStatus(leaseId, status);
    }

    @Override
    public MaintenanceRequest updateStatus(Long id, RequestStatus status) {
        MaintenanceRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return repository.save(request);
    }

}
