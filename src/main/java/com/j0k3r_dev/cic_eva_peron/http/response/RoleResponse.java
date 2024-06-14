package com.j0k3r_dev.cic_eva_peron.http.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private String name;

    private List<PermissionResponse> permissions;

}
