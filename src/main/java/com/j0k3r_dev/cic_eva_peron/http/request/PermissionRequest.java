package com.j0k3r_dev.cic_eva_peron.http.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequest {

    @NotBlank
    private String name;
}
