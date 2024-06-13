package com.j0k3r_dev.cic_eva_peron.users;

import com.j0k3r_dev.cic_eva_peron.http.request.UserRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse registryUser(UserRequest userRequest){


        return null;
    }

}
