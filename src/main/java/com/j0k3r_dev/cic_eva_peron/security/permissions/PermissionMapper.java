package com.j0k3r_dev.cic_eva_peron.security.permissions;

import com.j0k3r_dev.cic_eva_peron.http.response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    public static PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionResponse permissionEntityToPermissionResponse(PermissionEntity permissionEntity);
}
