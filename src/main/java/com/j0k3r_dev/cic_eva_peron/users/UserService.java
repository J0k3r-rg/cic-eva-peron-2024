package com.j0k3r_dev.cic_eva_peron.users;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.UserResponse;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleEntity;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserResponse registryUser(UserRequest userRequest){
        UserEntity user = UserMapper.INSTANCE.userReqiestToUserEntity(userRequest);
        Optional<RoleEntity> role = roleRepository.findByName("USER");
        if(role.isEmpty()){
             user.setRole(roleRepository.save(
                    new RoleEntity(
                            null,
                            "USER"
                    )
            ));
        }else {
            user.setRole(role.get());
        }
        userRepository.save(user);
        return UserMapper.INSTANCE.userEntityToUserResponse(user);
    }


}
