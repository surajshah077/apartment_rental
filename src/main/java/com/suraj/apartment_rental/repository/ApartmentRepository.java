package com.suraj.apartment_rental.repository;

import com.suraj.apartment_rental.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByAvailableTrue();
    List<Apartment> findByRentAmountLessThan(Double maxRent);
}
