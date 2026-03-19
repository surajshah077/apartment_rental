package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Apartment;
import java.util.List;

public interface ApartmentService extends BaseService<Apartment, Long> {
    List<Apartment> findByAvailableTrue();
    List<Apartment> findByRentAmountLessThan(Double maxRent);
}
