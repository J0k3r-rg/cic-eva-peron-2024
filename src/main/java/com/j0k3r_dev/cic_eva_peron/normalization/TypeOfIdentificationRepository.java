package com.j0k3r_dev.cic_eva_peron.normalization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfIdentificationRepository extends JpaRepository<TypeOfIdentification, String> {

    // despues me va a servir este metodo
    Optional<TypeOfIdentification> findByName(String name);

}
