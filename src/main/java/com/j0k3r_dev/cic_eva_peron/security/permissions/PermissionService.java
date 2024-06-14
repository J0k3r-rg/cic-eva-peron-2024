package com.j0k3r_dev.cic_eva_peron.security.permissions;

import com.j0k3r_dev.cic_eva_peron.http.request.PermissionRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.PermissionResponse;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleRepository;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public PermissionResponse createPermission(String permission, String token) throws PermissionException {
        if(permissionRepository.existsByName(permission)){
            throw new PermissionException("El permiso ya se encuentra registrado",495);
        }
        PermissionEntity permissionEntity = permissionRepository.save(
                PermissionEntity.builder()
                        .name(permission).build()
        );
        if(roleRepository.findByName("ROOT").isEmpty()){
            throw new PermissionException("El rol ROOT no se encuentra registrado",495);
        }
        roleRepository.findByName("ROOT").get().getPermissions().add(permissionEntity);
        return PermissionMapper.INSTANCE.permissionEntityToPermissionResponse(permissionEntity);
    }

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream().map(
                permission ->
                        PermissionResponse.builder()
                                .name(permission.getName())
                                .build()
        ).toList();
    }

    public List<PermissionEntity> obtainPermissionsByPermissionRequests(List<PermissionRequest> permissions) throws PermissionException {
        List<PermissionEntity> permissionsList = new ArrayList<>();
        for(PermissionRequest permissionRequest : permissions){
            Optional<PermissionEntity> permissionEntity = permissionRepository.findByName(permissionRequest.getName());
            if(permissionEntity.isEmpty()){
                throw new PermissionException("Permiso no encontrado",496);
            }
            permissionsList.add(permissionEntity.get());
        }
        return permissionsList;
    }
}
