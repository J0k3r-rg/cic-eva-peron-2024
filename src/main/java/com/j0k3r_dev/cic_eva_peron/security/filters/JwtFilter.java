package com.j0k3r_dev.cic_eva_peron.security.filters;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.j0k3r_dev.cic_eva_peron.security.JwtService;
import com.j0k3r_dev.cic_eva_peron.users.UserException;
import com.j0k3r_dev.cic_eva_peron.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, JWTDecodeException {

        if(getAuthorization(request) != null && getAuthorization(request).startsWith("Bearer ")){
            String token = getTokenAuthorization(getAuthorization(request));
            String id = request.getHeader("id");
            String subject = null;
            try{
                subject = jwtService.validateTokenLogin(token,id);
            } catch (JWTDecodeException | UserException e){
                throw new JWTDecodeException("El token es invalido");
            }
            if(subject!= null){
                UserDetails user = userRepository.findByUsername(subject).orElse(null);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);
    }

    private String getTokenAuthorization(String authorization){
        return authorization.replace("Bearer ","");
    }

    private String getAuthorization(HttpServletRequest request){
        return  request.getHeader("Authorization");
    }
}
