package com.suraj.apartment_rental.dto;

import lombok.Data;
import java.util.Date;

@Data
public class CreateLeaseRequest {
    private Long tenantId;
    private Long apartmentId;
    private Date startDate;
    private Date endDate;
}
