package com.suraj.apartment_rental.controller;

import com.suraj.apartment_rental.entity.MaintenanceRequest;
import com.suraj.apartment_rental.entity.MaintenanceRequest.RequestStatus;
import com.suraj.apartment_rental.service.MaintenanceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "*")
public class MaintenanceRequestController extends BaseController<MaintenanceRequest, Long> {

    public MaintenanceRequestController(MaintenanceRequestService service) {
        super(service);
    }

    @GetMapping("/lease/{leaseId}")
    public List<MaintenanceRequest> getByLease(@PathVariable Long leaseId) {
        return ((MaintenanceRequestService) service).findByLeaseId(leaseId);
    }

    @GetMapping("/lease/{leaseId}/pending")
    public List<MaintenanceRequest> getPendingByLease(@PathVariable Long leaseId) {
        return ((MaintenanceRequestService) service).findByLeaseIdAndStatus(leaseId, RequestStatus.PENDING);
    }

    @PostMapping("/{id}/in-progress")
    public MaintenanceRequest startWork(@PathVariable Long id) {
        return ((MaintenanceRequestService) service).updateStatus(id, RequestStatus.IN_PROGRESS);
    }

    @PostMapping("/{id}/completed")
    public MaintenanceRequest completeWork(@PathVariable Long id) {
        return ((MaintenanceRequestService) service).updateStatus(id, RequestStatus.COMPLETED);
    }
}
