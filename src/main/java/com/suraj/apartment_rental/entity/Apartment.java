package com.suraj.apartment_rental.entity;

import com.suraj.apartment_rental.entity.Lease;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apartments")
@Data @NoArgsConstructor @AllArgsConstructor
public class Apartment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String address;

    @Positive
    private Double rentAmount;

    @PositiveOrZero
    private Integer bedrooms;

    private Boolean available = true;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Lease> leases = new ArrayList<>();
}
