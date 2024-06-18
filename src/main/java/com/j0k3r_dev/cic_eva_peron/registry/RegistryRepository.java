package com.j0k3r_dev.cic_eva_peron.registry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends JpaRepository<RegistryEntity, String> {

}
