package com.j0k3r_dev.cic_eva_peron.security.filters.description;

import com.j0k3r_dev.cic_eva_peron.registry.RegistryEntity;
import jakarta.servlet.http.HttpServletRequest;

public interface ObtainDescription {
    void setNextObtainDescription(ObtainDescription nextInObtainDescriptionChain);
    void process(HttpServletRequest request, RegistryEntity.RegistryEntityBuilder registryBuilder);
}