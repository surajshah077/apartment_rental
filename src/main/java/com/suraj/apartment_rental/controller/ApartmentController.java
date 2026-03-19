package com.suraj.apartment_rental.controller;

import com.suraj.apartment_rental.entity.Apartment;
import com.suraj.apartment_rental.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@CrossOrigin(origins = "*")
public class ApartmentController extends BaseController<Apartment, Long> {

    public ApartmentController(ApartmentService service) {
        super(service);
    }

    @GetMapping("/available")
    public List<Apartment> getAvailable() {
        return ((ApartmentService) service).findByAvailableTrue();
    }

    @GetMapping("/search")
    public List<Apartment> search(@RequestParam Double maxRent) {
        return ((ApartmentService) service).findByRentAmountLessThan(maxRent);
    }
}
