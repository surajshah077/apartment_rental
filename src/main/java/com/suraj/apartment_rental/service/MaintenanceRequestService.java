package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.MaintenanceRequest;
import com.suraj.apartment_rental.entity.MaintenanceRequest.RequestStatus;
import java.util.List;

public interface MaintenanceRequestService extends BaseService<MaintenanceRequest, Long> {
    MaintenanceRequest save(MaintenanceRequest request);
    List<MaintenanceRequest> findAll();
    List<MaintenanceRequest> findByLeaseId(Long leaseId);
    List<MaintenanceRequest> findByLeaseIdAndStatus(Long leaseId, RequestStatus status);
    MaintenanceRequest updateStatus(Long id, RequestStatus status);
}
