package com.j0k3r_dev.cic_eva_peron.security.filters;

import com.j0k3r_dev.cic_eva_peron.registry.RegistryEntity;
import com.j0k3r_dev.cic_eva_peron.registry.RegistryRepository;
import com.j0k3r_dev.cic_eva_peron.security.CustomHttpServletResponseWrapper;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.security.MultiReadHttpServletRequest;
import com.j0k3r_dev.cic_eva_peron.security.filters.description.ObtainDescription;
import com.j0k3r_dev.cic_eva_peron.users.UserEntity;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class RegistryFilter extends OncePerRequestFilter {

    @Autowired
    private List<ObtainDescription> obtainDescriptionList;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = jwtService.getAuthorization(request);
        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtService.getTokenAuthorization(authorization);
        String id = request.getHeader("id");
        String subject = null;
        try {
            subject = jwtService.validateTokenLogin(token,id);
            System.out.println("subject: " + subject);
            System.out.println("id: " + id);
            RegistryEntity.RegistryEntityBuilder registryBuilder = RegistryEntity.builder()
                    .createdAt(LocalDateTime.now());
            obtainDescriptionList.get(0).process(request, registryBuilder);
            if ((registryBuilder.build().getDescription()!= null)){
                UserEntity userEntity = userRepository.findByUsername(subject).orElse(null);
                MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(request);
                String bodyCopy = IOUtils.toString(multiReadRequest.getReader());
                CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(response);
                filterChain.doFilter(multiReadRequest,responseWrapper);
                if(responseWrapper.getStatus() == HttpServletResponse.SC_OK) {
                    registryBuilder.userEntity(userEntity);
                    registryBuilder.description(registryBuilder.build().getDescription().concat(" - ").concat(bodyCopy));
                    registryRepository.save(registryBuilder.build());
                }
            }
            filterChain.doFilter(request,response);
        } catch (UserException e) {
        }
    }

}
