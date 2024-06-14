package com.j0k3r_dev.cic_eva_peron.security.permissions;

import com.j0k3r_dev.cic_eva_peron.http.response.PermissionResponse;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

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
}
