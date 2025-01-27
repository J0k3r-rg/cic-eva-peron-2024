package com.j0k3r_dev.cic_eva_peron.users;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.ProfileResponse;
import com.j0k3r_dev.cic_eva_peron.http.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Employee userRequestToEmployee(UserRequest userRequest);

    UserResponse userEntityToUserResponse(UserEntity userEntity);

    ProfileResponse userEntityToUserProfileResponse(UserEntity user);
}
