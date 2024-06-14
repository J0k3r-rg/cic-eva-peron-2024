package com.j0k3r_dev.cic_eva_peron.security.roles;

import com.j0k3r_dev.cic_eva_peron.http.request.RoleRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.RoleResponse;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionEntity;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest roleRequest){
        List<PermissionEntity> permissions = roleRequest.getPermissions().stream().map(
                permission -> permissionRepository.findByName(permission.getName())
                        .orElseThrow(null)
        ).toList();
        RoleEntity roleEntity = RoleEntity.builder()
                .name(roleRequest.getName())
                .permissions(permissions)
                .build();
        roleRepository.save(roleEntity);
        return RoleMapper.INSTANCE.roleEntityToRoleResponse(roleEntity);
    }


    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(
                RoleMapper.INSTANCE::roleEntityToRoleResponse
        ).toList();
    }
}
