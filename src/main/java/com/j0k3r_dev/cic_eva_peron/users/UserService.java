package com.j0k3r_dev.cic_eva_peron.users;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.UserResponse;
import com.j0k3r_dev.cic_eva_peron.security.permissions.PermissionRepository;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleEntity;
import com.j0k3r_dev.cic_eva_peron.security.roles.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse registryUser(UserRequest userRequest){
        Employee user = UserMapper.INSTANCE.userRequestToEmployee(userRequest);
        Optional<RoleEntity> role = roleRepository.findByName("USER");
        if(role.isEmpty()){
            throw new RuntimeException("Role not found");
        }else {
            user.setRole(role.get());
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return UserMapper.INSTANCE.userEntityToUserResponse(user);
    }

}
