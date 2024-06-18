package com.j0k3r_dev.cic_eva_peron.users.titular;

import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Titular extends UserEntity {

    private String typeOfIdentification;

    @Column(unique = true)
    private String identificationNumber;

    private LocalDate birthDate;

    private String birthplace;

    private String nationality;

    private String phone;

    private String address;

    private String neighborhood;

    private String city;

    private String postalCode;
}
