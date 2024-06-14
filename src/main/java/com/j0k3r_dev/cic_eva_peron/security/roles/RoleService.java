package com.j0k3r_dev.cic_eva_peron.security.roles;

import com.j0k3r_dev.cic_eva_peron.http.request.RoleRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.RoleResponse;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionEntity;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionException;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionService permissionService;

    @Transactional
    public RoleResponse createRole(RoleRequest roleRequest) throws PermissionException, RoleException {
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new RoleException("El rol ya se encuentra registrado en la base de datos",497);
        }
        List<PermissionEntity> permissions = permissionService.obtainPermissionsByPermissionRequests(roleRequest.getPermissions());
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

    @Transactional
    public RoleResponse updateRole(RoleRequest role) throws RoleException, PermissionException {
        Optional<RoleEntity> roleOptional = roleRepository.findById(role.getId());
        if(roleOptional.isEmpty()){
            throw new RoleException("El rol no se encuentra registrado en la base de datos",497);
        }
        RoleEntity roleEntity = roleOptional.get();
        List<PermissionEntity> permissions = permissionService.obtainPermissionsByPermissionRequests(role.getPermissions());
        roleEntity.setName(role.getName());
        roleEntity.setPermissions(permissions);
        return RoleMapper.INSTANCE.roleEntityToRoleResponse(roleEntity);
    }
}
