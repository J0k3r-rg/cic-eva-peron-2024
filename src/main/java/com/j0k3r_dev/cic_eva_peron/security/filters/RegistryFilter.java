package com.j0k3r_dev.cic_eva_peron.security.filters;

import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.security.filters.description.ObtainDescription;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
public class RegistryFilter extends OncePerRequestFilter {

    @Autowired
    private List<ObtainDescription> obtainDescriptionList;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("------------------------------------------------------------------------------------------");
        String authorization = jwtService.getAuthorization(request);
        String token = jwtService.getTokenAuthorization(authorization);
        String subject = jwtService.validateAndGetSubject(token);
        String description = getDescription(request);
        System.out.println("Descripcion: "+subject + " - "+description);

        System.out.println("------------------------------------------------------------------------------------------");
        filterChain.doFilter(request,response);
    }

    private String getDescription(HttpServletRequest request){
        for(ObtainDescription obtainDescription: obtainDescriptionList){
            if(obtainDescription.getDescription(request) != null){
                return obtainDescription.getDescription(request);
            }
        }
        return null;
    }

}
