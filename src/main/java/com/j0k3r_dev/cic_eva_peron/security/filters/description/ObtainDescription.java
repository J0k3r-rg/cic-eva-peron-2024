package com.j0k3r_dev.cic_eva_peron.security.filters.description;

import jakarta.servlet.http.HttpServletRequest;

public interface ObtainDescription {

    String getDescription(HttpServletRequest request);

}
