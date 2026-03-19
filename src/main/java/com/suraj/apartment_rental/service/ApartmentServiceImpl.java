package com.suraj.apartment_rental.service;

import com.suraj.apartment_rental.entity.Apartment;
import com.suraj.apartment_rental.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository repository;

    @Override
    public Apartment save(Apartment apartment) {
        if (apartment.getRentAmount() == null || apartment.getRentAmount() < 500) {
            throw new IllegalArgumentException("Rent must be at least 500€");
        }
        apartment.setAvailable(true); // New apartments are available
        return repository.save(apartment);
    }

    @Override
    public List<Apartment> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Apartment> findByAvailableTrue() {
        return repository.findByAvailableTrue();
    }

    @Override
    public List<Apartment> findByRentAmountLessThan(Double maxRent) {
        return repository.findByRentAmountLessThan(maxRent);
    }

    @Override
    public Apartment findById(Long id) {
        Optional<Apartment> apartment = repository.findById(id);
        return apartment.orElseThrow(() -> new RuntimeException("Apartment not found: " + id));
    }

    @Override
    public Apartment update(Long id, Apartment apartmentDetails) {
        Apartment apartment = findById(id);
        apartment.setAddress(apartmentDetails.getAddress());
        apartment.setRentAmount(apartmentDetails.getRentAmount());
        apartment.setBedrooms(apartmentDetails.getBedrooms());
        apartment.setAvailable(apartmentDetails.getAvailable() != null ? apartmentDetails.getAvailable() : apartment.getAvailable());
        return repository.save(apartment);
    }

    @Override
    public void delete(Long id) {
        Apartment apartment = findById(id);
        // Mark as unavailable instead of deleting (business logic)
        apartment.setAvailable(false);
        repository.save(apartment);
    }
}
