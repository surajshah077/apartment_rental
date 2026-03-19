package com.suraj.apartment_rental.repository;

import com.suraj.apartment_rental.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {
    List<MaintenanceRequest> findByLeaseId(Long leaseId);
    List<MaintenanceRequest> findByLeaseIdAndStatus(Long leaseId, MaintenanceRequest.RequestStatus status);

}
