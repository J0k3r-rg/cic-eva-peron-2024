package com.j0k3r_dev.cic_eva_peron.security.roles;

import com.j0k3r_dev.cic_eva_peron.http.request.RoleRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    public static RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleEntity roleRequestToRoleEntity(RoleRequest roleRequest);

    RoleResponse roleEntityToRoleResponse(RoleEntity roleEntity);


}
