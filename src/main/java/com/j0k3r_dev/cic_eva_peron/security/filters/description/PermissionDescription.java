package com.j0k3r_dev.cic_eva_peron.security.filters.description;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class PermissionDescription implements ObtainDescription {

    @Override
    public String getDescription(HttpServletRequest request) {
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/permission/save/")){
            return " ha registrado un permiso";
        }
        if(request.getMethod().equals("POST") && request.getRequestURI().contains("/api/role/create")){
            return " ha registrado un rol";
        }
        return null;
    }
}
