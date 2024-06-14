package com.j0k3r_dev.cic_eva_peron.http.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {

    @NotBlank
    private String name;

    @NotNull
    @Valid
    private List<PermissionRequest> permissions;

}
