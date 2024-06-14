package com.j0k3r_dev.cic_eva_peron.security.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

    Optional<PermissionEntity> findByName(String name);

    boolean existsByName(String permission);
}
