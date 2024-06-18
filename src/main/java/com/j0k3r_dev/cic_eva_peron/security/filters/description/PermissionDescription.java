package com.j0k3r_dev.cic_eva_peron.security.filters.description;

import com.j0k3r_dev.cic_eva_peron.registry.RegistryEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class PermissionDescription implements ObtainDescription {

    private ObtainDescription nextInObtainDescriptionChain;

    @Override
    public void setNextObtainDescription(ObtainDescription nextInObtainDescriptionChain) {
        this.nextInObtainDescriptionChain = nextInObtainDescriptionChain;
    }

    @Override
    public void process(HttpServletRequest request, RegistryEntity.RegistryEntityBuilder registryBuilder) {
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/permission/save/")){
            registryBuilder.description(" ha registrado un permiso - "+ request.getRequestURI().replace("/api/permission/save/",""));
        } else if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/role/create")){
            registryBuilder.description(" ha registrado un rol");
        } else if (nextInObtainDescriptionChain != null) {
            nextInObtainDescriptionChain.process(request, registryBuilder);
        }
    }
}
