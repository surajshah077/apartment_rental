package com.suraj.apartment_rental.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tenants")
@Data @NoArgsConstructor @AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @Email
    private String email;

    @Positive
    private Double income;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<Lease> leases = new ArrayList<>();
}
