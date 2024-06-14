package com.j0k3r_dev.cic_eva_peron.profile;

import com.j0k3r_dev.cic_eva_peron.http.request.ProfileRequest;
import com.j0k3r_dev.cic_eva_peron.http.response.ProfileResponse;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserMapper;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ProfileResponse updateProfile(ProfileRequest profileRequest) throws ProfileException {
        Optional<UserEntity> userOptional = userRepository.findById(profileRequest.getId());
        if(userOptional.isEmpty()){
            throw new ProfileException("Usuario no encontrado", 496);
        }
        UserEntity user = userOptional.get();
        if(profileRequest.getEmail()!= null && !profileRequest.getEmail().isEmpty()){
            user.setEmail(profileRequest.getEmail());
        }
        if(profileRequest.getUsername()!= null && !profileRequest.getUsername().isEmpty()){
            user.setUsername(profileRequest.getUsername());
        }
        if(profileRequest.getUrlImage()!= null && !profileRequest.getUrlImage().isEmpty()){
            user.setUrlImage(profileRequest.getUrlImage());
        }
        return UserMapper.INSTANCE.userEntityToUserProfileResponse(user);
    }
}
